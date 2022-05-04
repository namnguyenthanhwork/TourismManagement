package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMCategoryRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.BillEntity;
import com.ou.pojos.CategoryEntity;
import com.ou.pojos.StorageEntity;
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
public class CMCategoryRepositoryImpl implements CMCategoryRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

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
                        storageEntityRoot.get("storName"), storageEntityRoot.get("storSlug"))
                .orderBy(criteriaBuilder.asc(categoryEntityRoot.get("catId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getCategoryAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(categoryEntityRoot.get("catId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public CategoryEntity getCategory(String catSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(categoryEntityRoot.get("catSlug").as(String.class), catSlug));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public CategoryEntity getCategory(Integer catId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(categoryEntityRoot.get("catId").as(Integer.class), catId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public List<CategoryEntity> getCategoriesByStorageSlug(String storSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        Root<StorageEntity> storageEntityRoot = criteriaQuery.from(StorageEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(categoryEntityRoot.get("storId").as(Integer.class),
                                storageEntityRoot.get("storId").as(Integer.class)),
                        criteriaBuilder.equal(storageEntityRoot.get("storSlug").as(String.class), storSlug))
                .multiselect(categoryEntityRoot.get("catId"), categoryEntityRoot.get("catName"),
                        categoryEntityRoot.get("catSlug"));
        List<CategoryEntity> categoryEntities =
                pojoBeanFactory.getApplicationContext().getBean("categoryEntities", List.class);
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        results.forEach(result->{
            CategoryEntity category = pojoBeanFactory.getApplicationContext().getBean(CategoryEntity.class);
            category.setCatId((Integer) result[0]);
            category.setCatName(result[1].toString());
            category.setCatSlug((String) result[2]);
            categoryEntities.add(category);
        });

        return categoryEntities;
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
