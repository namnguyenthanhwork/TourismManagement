package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMTourServiceRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.ServiceEntity;
import com.ou.pojos.TourEntity;
import com.ou.pojos.TourServiceEntity;
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
public class CMTourServiceRepositoryImpl implements CMTourServiceRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<TourServiceEntity> getTourServiceByTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourServiceEntity> tourServiceEntityRoot = criteriaQuery.from(TourServiceEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(tourServiceEntityRoot.get("tourId").as(Integer.class),
                                tourEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)),
                        criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), tourSlug))
                .multiselect(tourServiceEntityRoot.get("tourId"), tourServiceEntityRoot.get("servId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourServiceEntity> tourServices = pojoBeanFactory.getApplicationContext().getBean("tourServiceEntityList", List.class);
        results.forEach(result -> {
            TourServiceEntity tourService = pojoBeanFactory.getApplicationContext().getBean(TourServiceEntity.class);
            tourService.setTourId((Integer) result[0]);
            tourService.setServId((Integer) result[1]);
            tourServices.add(tourService);
        });

        return tourServices;
    }

    @Override
    public List<TourServiceEntity> getTourServiceByService(String servSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourServiceEntity> tourServiceEntityRoot = criteriaQuery.from(TourServiceEntity.class);
        Root<ServiceEntity> serviceEntityRoot = criteriaQuery.from(ServiceEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(tourServiceEntityRoot.get("servId").as(Integer.class),
                        serviceEntityRoot.get("servId").as(Integer.class)),
                criteriaBuilder.equal(serviceEntityRoot.get("servSlug").as(String.class), servSlug))
                .multiselect(tourServiceEntityRoot.get("tourId"), tourServiceEntityRoot.get("servId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<TourServiceEntity> tourServices = pojoBeanFactory.getApplicationContext().getBean("tourServiceEntityList", List.class);
        results.forEach(result -> {
            TourServiceEntity tourService = pojoBeanFactory.getApplicationContext().getBean(TourServiceEntity.class);
            tourService.setTourId((Integer) result[0]);
            tourService.setServId((Integer) result[1]);
            tourServices.add(tourService);
        });
        return tourServices;
    }

    @Override
    public boolean createTourService(TourServiceEntity tourServiceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(tourServiceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateTourService(TourServiceEntity tourServiceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(tourServiceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteTourService(TourServiceEntity tourServiceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(tourServiceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
