package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMPaymentTypeRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PaymentTypeEntity;
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
public class CMPaymentTypeRepositoryImpl implements CMPaymentTypeRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getPaymentTypes(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        criteriaQuery.multiselect(paymentTypeEntityRoot.get("paytId"), paymentTypeEntityRoot.get("paytName"),
                paymentTypeEntityRoot.get("paytSlug"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public PaymentTypeEntity getPaymentType(String paytSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentTypeEntity> criteriaQuery = criteriaBuilder.createQuery(PaymentTypeEntity.class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(paymentTypeEntityRoot.get("paytSlug").as(String.class), paytSlug));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public PaymentTypeEntity getPaymentType(Integer paytId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PaymentTypeEntity> criteriaQuery = criteriaBuilder.createQuery(PaymentTypeEntity.class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(paymentTypeEntityRoot.get("paytId").as(Integer.class), paytId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createPaymentType(PaymentTypeEntity paymentTypeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(paymentTypeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updatePaymentType(PaymentTypeEntity paymentTypeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(paymentTypeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deletePaymentType(PaymentTypeEntity paymentTypeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(paymentTypeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
