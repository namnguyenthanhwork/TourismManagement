package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMSaleRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SaleEntity;
import com.ou.pojos.SalePercentEntity;
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
public class CMSaleRepositoryImpl implements CMSaleRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getSales(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<SaleEntity> saleEntityRoot = criteriaQuery.from(SaleEntity.class);
        Root<SalePercentEntity> salePercentEntityRoot = criteriaQuery.from(SalePercentEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(saleEntityRoot.get("sperId"), salePercentEntityRoot.get("sperId")))
                .multiselect(saleEntityRoot.get("saleId"), saleEntityRoot.get("saleFromDate"),
                        saleEntityRoot.get("saleToDate"), salePercentEntityRoot.get("sperId"),
                        salePercentEntityRoot.get("sperPercent"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public SaleEntity getSale(Integer saleId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SaleEntity> criteriaQuery = criteriaBuilder.createQuery(SaleEntity.class);
        Root<SaleEntity> saleEntityRoot = criteriaQuery.from(SaleEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(saleEntityRoot.get("saleId").as(Integer.class), saleId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createSale(SaleEntity saleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(saleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateSale(SaleEntity saleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(saleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteSale(SaleEntity saleEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(saleEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
