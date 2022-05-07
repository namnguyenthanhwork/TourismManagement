package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourDepartureDateEntity;
import com.ou.pojos.TourEntity;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
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
                        postEntityRoot.get("postContent"), postEntityRoot.get("postCoverPage"))
                .orderBy(criteriaBuilder.asc(tourEntityRoot.get("tourId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> getTours() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)))
                .multiselect(postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getTourAmount(String ... params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        if (params != null && params.length > 0 && params[0] != null) {
            Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
            criteriaQuery.where(
                    criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                            postEntityRoot.get("postId").as(Integer.class)),
                    criteriaBuilder.like(postEntityRoot.get("postTitle").as(String.class),
                            String.format("%%%s%%", params[0].trim())));
        }
        criteriaQuery.multiselect(criteriaBuilder.count(tourEntityRoot.get("tourId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public long getTotalTourSlot(Integer tourId, Timestamp timestamp) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<TourDepartureDateEntity> tourDepartureDateEntityRoot = criteriaQuery.from(TourDepartureDateEntity.class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                tourDepartureDateEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourDepartureDateEntityRoot.get("dptId").as(Integer.class),
                                departureDateEntityRoot.get("dptId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId"), tourId),
                        criteriaBuilder.greaterThanOrEqualTo(departureDateEntityRoot.get("dptDate").as(Timestamp.class), timestamp))
                .multiselect(criteriaBuilder.sum(tourDepartureDateEntityRoot.get("tourAmount")));

        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception noResultException) {
            return 0;
        }
    }

    @Override
    public long getTotalSellTourSlot(Integer tourId, Timestamp timestamp) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<TourDepartureDateEntity> tourDepartureDateEntityRoot = criteriaQuery.from(TourDepartureDateEntity.class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                tourDepartureDateEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourDepartureDateEntityRoot.get("dptId").as(Integer.class),
                                departureDateEntityRoot.get("dptId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId"), tourId),
                        criteriaBuilder.greaterThanOrEqualTo(departureDateEntityRoot.get("dptDate").as(Timestamp.class),
                                timestamp))
                .multiselect(criteriaBuilder.sum(tourDepartureDateEntityRoot.get("tourSellAmount")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception noResultException) {
            return 0;
        }
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
                        tourEntityRoot.get("catId"), tourEntityRoot.get("saleId")
                );
        try {
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
        }catch (NoResultException noResultException){
            return null;
        }

    }

    @Override
    public TourEntity getTour(Integer tourId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TourEntity> criteriaQuery = criteriaBuilder.createQuery(TourEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class), tourId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
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
