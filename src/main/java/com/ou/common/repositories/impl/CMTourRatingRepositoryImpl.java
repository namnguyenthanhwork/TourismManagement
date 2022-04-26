package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourRatingRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourEntity;
import com.ou.pojos.TourRatingEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMTourRatingRepositoryImpl implements CMTourRatingRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<TourRatingEntity> getTourRatingByAccount(String accUsername) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourRatingEntity> tourRatingEntityRoot = criteriaQuery.from(TourRatingEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourRatingEntityRoot.get("accId").as(Integer.class),
                                accountEntityRoot.get("accId").as(Integer.class)),
                        criteriaBuilder.equal(accountEntityRoot.get("accUsername").as(String.class), accUsername))
                .multiselect(
                        tourRatingEntityRoot.get("tourId"), tourRatingEntityRoot.get("accId"),
                        tourRatingEntityRoot.get("rateAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourRatingEntity> tourRatings = pojoBeanFactory.getApplicationContext()
                .getBean("tourRatingEntityList", List.class);
        results.forEach(result -> {
            TourRatingEntity tourRating = pojoBeanFactory.getApplicationContext()
                    .getBean(TourRatingEntity.class);
            tourRating.setTourId((Integer) result[0]);
            tourRating.setAccId((Integer) result[1]);
            tourRating.setRateAmount((Integer) result[2]);

            tourRatings.add(tourRating);
        });
        return tourRatings;
    }

    @Override
    public List<TourRatingEntity> getTourRatingByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourRatingEntity> tourRatingEntityRoot = criteriaQuery.from(TourRatingEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourRatingEntityRoot.get("tourId").as(Integer.class),
                                tourEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)),
                        criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(
                        tourRatingEntityRoot.get("tourId"), tourRatingEntityRoot.get("accId"),
                        tourRatingEntityRoot.get("rateAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourRatingEntity> tourRatings = pojoBeanFactory.getApplicationContext()
                .getBean("tourRatingEntityList", List.class);
        results.forEach(result -> {
            TourRatingEntity tourRating = pojoBeanFactory.getApplicationContext()
                    .getBean(TourRatingEntity.class);
            tourRating.setTourId((Integer) result[0]);
            tourRating.setAccId((Integer) result[1]);
            tourRating.setRateAmount((Integer) result[2]);

            tourRatings.add(tourRating);
        });
        return tourRatings;
    }

    @Override
    public boolean createTourRating(TourRatingEntity tourRatingEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourRatingEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourRating(TourRatingEntity tourRatingEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourRatingEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourRating(TourRatingEntity tourRatingEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourRatingEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
