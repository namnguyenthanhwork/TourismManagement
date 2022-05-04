package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMDepartureDateRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMDepartureDateRepositoryImpl implements CMDepartureDateRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<Object[]> getDepartureDates(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        Root<FeatureEntity> featureEntityRoot = criteriaQuery.from(FeatureEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(departureDateEntityRoot.get("feaId"), featureEntityRoot.get("feaId")))
                .multiselect(departureDateEntityRoot.get("dptId"), departureDateEntityRoot.get("dptDate"),
                        featureEntityRoot.get("feaId"), featureEntityRoot.get("feaName"), featureEntityRoot.get("feaSlug"))
                .orderBy(criteriaBuilder.asc(departureDateEntityRoot.get("dptId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getDepartureDateAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(departureDateEntityRoot.get("dptId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public DepartureDateEntity getDepartureDate(Integer dptId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DepartureDateEntity> criteriaQuery = criteriaBuilder.createQuery(DepartureDateEntity.class);
        Root<DepartureDateEntity> departureDateEntityRoot = criteriaQuery.from(DepartureDateEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(departureDateEntityRoot.get("dptId").as(Integer.class), dptId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createDepartureDate(DepartureDateEntity departureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(departureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateDepartureDate(DepartureDateEntity departureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(departureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteDepartureDate(DepartureDateEntity departureDateEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(departureDateEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
