package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourDepartureDateRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourDepartureDateEntity;
import com.ou.pojos.TourEntity;
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
public class CMTourDepartureDateRepositoryImpl implements CMTourDepartureDateRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;
    @Override
    public List<TourDepartureDateEntity> getTourDepartureDateByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourDepartureDateEntity> tourDepartureDateEntityRoot = criteriaQuery.from(TourDepartureDateEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourDepartureDateEntityRoot.get("tourId").as(Integer.class),
                        tourEntityRoot.get("tourId").as(Integer.class)),
                criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                        postEntityRoot.get("postId").as(Integer.class)),
                criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(
                        tourDepartureDateEntityRoot.get("tourId"), tourDepartureDateEntityRoot.get("dptId"),
                        tourDepartureDateEntityRoot.get("tourAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourDepartureDateEntity> tourDepartureDates = pojoBeanFactory.getApplicationContext()
                .getBean("tourDepartureDateEntityList", List.class);
        results.forEach(result -> {
            TourDepartureDateEntity tourDepartureDate = pojoBeanFactory.getApplicationContext()
                    .getBean(TourDepartureDateEntity.class);
            tourDepartureDate.setTourId((Integer) result[0]);
            tourDepartureDate.setDptId((Integer) result[1]);
            tourDepartureDate.setTourAmount((Integer) result[2]);
            tourDepartureDates.add(tourDepartureDate);
        });
        return tourDepartureDates;
    }

    @Override
    public List<TourDepartureDateEntity> getTourDepartureDateByDepartureDate(Integer dptId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourDepartureDateEntity> tourDepartureDateEntityRoot = criteriaQuery.from(TourDepartureDateEntity.class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourDepartureDateEntityRoot.get("dptId").as(Integer.class),
                        departureDateEntityRoot.get("dptId").as(Integer.class)),
                criteriaBuilder.equal(departureDateEntityRoot.get("dptId").as(Integer.class), dptId))
                .multiselect(
                        tourDepartureDateEntityRoot.get("tourId"), tourDepartureDateEntityRoot.get("dptId"),
                        tourDepartureDateEntityRoot.get("tourAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourDepartureDateEntity> tourDepartureDates = pojoBeanFactory.getApplicationContext()
                .getBean("tourDepartureDateEntityList", List.class);
        results.forEach(result -> {
            TourDepartureDateEntity tourDepartureDate = pojoBeanFactory.getApplicationContext()
                    .getBean(TourDepartureDateEntity.class);
            tourDepartureDate.setTourId((Integer) result[0]);
            tourDepartureDate.setDptId((Integer) result[1]);
            tourDepartureDate.setTourAmount((Integer) result[2]);
            tourDepartureDates.add(tourDepartureDate);
        });
        return tourDepartureDates;
    }

    @Override
    public boolean createTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourDepartureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourDepartureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourDepartureDate(TourDepartureDateEntity tourDepartureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourDepartureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
