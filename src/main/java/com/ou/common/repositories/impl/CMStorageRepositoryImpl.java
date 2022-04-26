package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMStorageRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.StorageEntity;
import com.ou.utils.PageUtil;
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
public class CMStorageRepositoryImpl implements CMStorageRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getStorages(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<StorageEntity> storageEntityRoot = criteriaQuery.from(StorageEntity.class);
        criteriaQuery.multiselect(storageEntityRoot.get("storId"), storageEntityRoot.get("storName"),
                storageEntityRoot.get("storSlug"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public StorageEntity getStorage(String storSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StorageEntity> criteriaQuery = criteriaBuilder.createQuery(StorageEntity.class);
        Root<StorageEntity> storageEntityRoot = criteriaQuery.from(StorageEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(storageEntityRoot.get("storSlug").as(String.class), storSlug));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public StorageEntity getStorage(Integer storId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<StorageEntity> criteriaQuery = criteriaBuilder.createQuery(StorageEntity.class);
        Root<StorageEntity> storageEntityRoot = criteriaQuery.from(StorageEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(storageEntityRoot.get("storId").as(Integer.class), storId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createStorage(StorageEntity storageEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(storageEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateStorage(StorageEntity storageEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(storageEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteStorage(StorageEntity storageEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(storageEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
