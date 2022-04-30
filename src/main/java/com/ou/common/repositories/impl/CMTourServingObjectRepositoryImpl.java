package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMTourServingObjectRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.ServingObjectEntity;
import com.ou.pojos.TourEntity;
import com.ou.pojos.TourServingObjectEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMTourServingObjectRepositoryImpl implements CMTourServingObjectRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;
    @Override
    public List<TourServingObjectEntity> getTourServingObjectByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourServingObjectEntityRoot.get("tourId").as(Integer.class),
                        tourEntityRoot.get("tourId").as(Integer.class)),
                criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                        postEntityRoot.get("postId").as(Integer.class)),
                criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(
                        tourServingObjectEntityRoot.get("tourId"),tourServingObjectEntityRoot.get("svoId"),
                        tourServingObjectEntityRoot.get("tourPrice"));

        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourServingObjectEntity> tourServingObjects = pojoBeanFactory.getApplicationContext()
                .getBean("tourServingObjectEntityList", List.class);
        results.forEach(result -> {
            TourServingObjectEntity tourServingObject = pojoBeanFactory.getApplicationContext()
                    .getBean(TourServingObjectEntity.class);
            tourServingObject.setTourId((Integer) result[0]);
            tourServingObject.setSvoId((Integer) result[1]);
            tourServingObject.setTourPrice((BigDecimal) result[2]);
            tourServingObjects.add(tourServingObject);
        });

        return tourServingObjects;
    }

    @Override
    public List<TourServingObjectEntity> getTourServingObjectByServingObject(String svoSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
        Root<ServingObjectEntity> servingObjectEntityRoot = criteriaQuery.from(ServingObjectEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourServingObjectEntityRoot.get("svoId").as(Integer.class),
                        servingObjectEntityRoot.get("svoId").as(Integer.class)),
                criteriaBuilder.equal(servingObjectEntityRoot.get("svoSlug").as(String.class), svoSlug))
                .multiselect(
                tourServingObjectEntityRoot.get("tourId"),tourServingObjectEntityRoot.get("svoId"),
                tourServingObjectEntityRoot.get("tourPrice"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourServingObjectEntity> tourServingObjects = pojoBeanFactory.getApplicationContext()
                .getBean("tourServingObjectEntityList", List.class);
        results.forEach(result -> {
            TourServingObjectEntity tourServingObject = pojoBeanFactory.getApplicationContext()
                    .getBean(TourServingObjectEntity.class);
            tourServingObject.setTourId((Integer) result[0]);
            tourServingObject.setSvoId((Integer) result[1]);
            tourServingObject.setTourPrice((BigDecimal) result[2]);
            tourServingObjects.add(tourServingObject);
        });

        return tourServingObjects;
    }

    @Override
    public TourServingObjectEntity getTourServingObjectById(Integer tsvoId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TourServingObjectEntity> criteriaQuery = criteriaBuilder.createQuery(TourServingObjectEntity.class);
        Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(tourServingObjectEntityRoot.get("tsvoId").as(Integer.class), tsvoId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourServingObject(TourServingObjectEntity tourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
