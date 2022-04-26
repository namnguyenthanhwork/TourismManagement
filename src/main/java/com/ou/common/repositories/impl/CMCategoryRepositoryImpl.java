package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMCategoryRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.CategoryEntity;
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
public class CMCategoryRepositoryImpl implements CMCategoryRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getCategories(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        Root<StorageEntity> storageEntityRoot = criteriaQuery.from(StorageEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(categoryEntityRoot.get("storId"), storageEntityRoot.get("storId")))
                .multiselect(categoryEntityRoot.get("catId"), categoryEntityRoot.get("catName"),
                        categoryEntityRoot.get("catSlug"), storageEntityRoot.get("storId"),
                        storageEntityRoot.get("storName"), storageEntityRoot.get("storSlug"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public CategoryEntity getCategory(String catSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(categoryEntityRoot.get("catSlug").as(String.class), catSlug));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public CategoryEntity getCategory(Integer catId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(categoryEntityRoot.get("catId").as(Integer.class), catId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createCategory(CategoryEntity categoryEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(categoryEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateCategory(CategoryEntity categoryEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(categoryEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteCategory(CategoryEntity categoryEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(categoryEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
