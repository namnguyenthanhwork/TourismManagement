package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMFeatureRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.FeatureEntity;
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
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional
public class CMFeatureRepositoryImpl implements CMFeatureRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    @Override
    public List<Object[]> getFeatures(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<FeatureEntity> featureEntityRoot = criteriaQuery.from(FeatureEntity.class);
        criteriaQuery.multiselect(featureEntityRoot.get("feaId"), featureEntityRoot.get("feaName"),
                featureEntityRoot.get("feaSlug"))
                .orderBy(criteriaBuilder.asc(featureEntityRoot.get("feaId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getFeatureAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<FeatureEntity> featureEntityRoot = criteriaQuery.from(FeatureEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(featureEntityRoot.get("feaId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public FeatureEntity getFeature(String feaSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FeatureEntity> criteriaQuery = criteriaBuilder.createQuery(FeatureEntity.class);
        Root<FeatureEntity> featureEntityRoot = criteriaQuery.from(FeatureEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(featureEntityRoot.get("feaSlug").as(String.class), feaSlug));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public FeatureEntity getFeature(Integer feaId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FeatureEntity> criteriaQuery = criteriaBuilder.createQuery(FeatureEntity.class);
        Root<FeatureEntity> featureEntityRoot = criteriaQuery.from(FeatureEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(featureEntityRoot.get("feaId").as(Integer.class), feaId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createFeature(FeatureEntity featureEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(featureEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateFeature(FeatureEntity featureEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(featureEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteFeature(FeatureEntity featureEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(featureEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
