package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMServingObjectRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ServingObjectEntity;
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
public class CMServingObjectRepositoryImpl implements CMServingObjectRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getServingObjects(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ServingObjectEntity> servingObjectEntityRoot = criteriaQuery.from(ServingObjectEntity.class);
        criteriaQuery.multiselect(servingObjectEntityRoot.get("svoId"), servingObjectEntityRoot.get("svoName"),
                servingObjectEntityRoot.get("svoSlug"))
                .orderBy(criteriaBuilder.asc(servingObjectEntityRoot.get("svoId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getServingObjectAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<ServingObjectEntity> servingObjectEntityRoot = criteriaQuery.from(ServingObjectEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(servingObjectEntityRoot.get("svoId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public ServingObjectEntity getServingObject(String svoSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ServingObjectEntity> criteriaQuery = criteriaBuilder.createQuery(ServingObjectEntity.class);
        Root<ServingObjectEntity> servingObjectEntityRoot = criteriaQuery.from(ServingObjectEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(servingObjectEntityRoot.get("svoSlug").as(String.class), svoSlug));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public ServingObjectEntity getServingObject(Integer svoId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ServingObjectEntity> criteriaQuery = criteriaBuilder.createQuery(ServingObjectEntity.class);
        Root<ServingObjectEntity> servingObjectEntityRoot = criteriaQuery.from(ServingObjectEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(servingObjectEntityRoot.get("svoId").as(Integer.class), svoId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createServingObject(ServingObjectEntity servingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(servingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateServingObject(ServingObjectEntity servingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(servingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteServingObject(ServingObjectEntity servingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(servingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
