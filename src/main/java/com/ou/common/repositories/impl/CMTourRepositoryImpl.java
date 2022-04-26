package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourEntity;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMTourRepositoryImpl implements CMTourRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<Object[]> getTours(Integer pageIndex, String ... params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)));

        if (params != null && params.length > 0 && params[0]!=null) {
            predicates.add(criteriaBuilder.like(postEntityRoot.get("postTitle").as(String.class),
                    String.format("%%%s%%", params[0].trim())));
        }

        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                .getBean("predicateArray", Predicate[].class)))
                .multiselect(
                        tourEntityRoot.get("tourId"), tourEntityRoot.get("tourAverageRating"),
                        tourEntityRoot.get("catId"), tourEntityRoot.get("saleId"),
                        postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postContent"), postEntityRoot.get("postCoverPage"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public TourEntity getTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)),
                        criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(
                        tourEntityRoot.get("tourId"), tourEntityRoot.get("tourAverageRating"),
                        tourEntityRoot.get("catId"), tourEntityRoot.get("saleId"),
                        postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postContent"), postEntityRoot.get("postCoverPage")
                );
        Object[] result = session.createQuery(criteriaQuery).getSingleResult();
        TourEntity tour = pojoBeanFactory.getApplicationContext().getBean(TourEntity.class);
        tour.setTourId((Integer) result[0]);
        tour.setTourAverageRating((Integer) result[1]);
        tour.setCatId((Integer) result[2]);
        if (result[3] != null)
            tour.setSaleId((Integer) result[3]);
        else
            tour.setSaleId(null);
        return tour;
    }

    @Override
    public TourEntity getTour(Integer tourId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TourEntity> criteriaQuery = criteriaBuilder.createQuery(TourEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class), tourId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createTour(TourEntity tourEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTour(TourEntity tourEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTour(TourEntity tourEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
