package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMTourTransportRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourEntity;
import com.ou.pojos.TourTransportEntity;
import com.ou.pojos.TransportEntity;
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
public class CMTourTransportRepositoryImpl implements CMTourTransportRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;


    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<TourTransportEntity> getTourTransportByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourTransportEntity> tourTransportEntityRoot = criteriaQuery.from(TourTransportEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourTransportEntityRoot.get("tourId").as(Integer.class),
                                tourEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)),
                        criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(tourTransportEntityRoot.get("tourId"), tourTransportEntityRoot.get("tranId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourTransportEntity> tourTransports = pojoBeanFactory.getApplicationContext()
                .getBean("tourServingObjectEntityList", List.class);
        results.forEach(result -> {
            TourTransportEntity tourTransport = pojoBeanFactory.getApplicationContext().getBean(TourTransportEntity.class);
            tourTransport.setTourId((Integer) result[0]);
            tourTransport.setTranId((Integer) result[1]);
            tourTransports.add(tourTransport);
        });

        return tourTransports;
    }

    @Override
    public List<TourTransportEntity> getTourTransportByTransport(String tranSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourTransportEntity> tourTransportEntityRoot = criteriaQuery.from(TourTransportEntity.class);
        Root<TransportEntity> transportEntityRoot = criteriaQuery.from(TransportEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourTransportEntityRoot.get("tranId").as(Integer.class),
                                transportEntityRoot.get("tranId").as(Integer.class)),
                        criteriaBuilder.equal(transportEntityRoot.get("tranSlug").as(String.class), tranSlug))
                .multiselect(tourTransportEntityRoot.get("tourId"), tourTransportEntityRoot.get("tranId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourTransportEntity> tourTransports = pojoBeanFactory.getApplicationContext()
                .getBean("tourServingObjectEntityList", List.class);
        results.forEach(result -> {
            TourTransportEntity tourTransport = pojoBeanFactory.getApplicationContext().getBean(TourTransportEntity.class);
            tourTransport.setTourId((Integer) result[0]);
            tourTransport.setTranId((Integer) result[1]);
            tourTransports.add(tourTransport);
        });

        return tourTransports;
    }

    @Override
    public boolean createTourTransport(TourTransportEntity tourTransportEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourTransportEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourTransport(TourTransportEntity tourTransportEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourTransportEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourTransport(TourTransportEntity tourTransportEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourTransportEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
