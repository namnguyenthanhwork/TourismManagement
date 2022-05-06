package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMBillRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.BillEntity;
import com.ou.pojos.PaymentTypeEntity;
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
public class CMBillRepositoryImpl implements CMBillRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getBills(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(billEntityRoot.get("accId").as(Integer.class),
                                accountEntityRoot.get("accId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("paytId").as(Integer.class),
                                paymentTypeEntityRoot.get("paytId").as(Integer.class)))
                .multiselect( accountEntityRoot.get("accUsername"),paymentTypeEntityRoot.get("paytName"),
                        billEntityRoot.get("billId"), billEntityRoot.get("billCreatedDate"),
                        billEntityRoot.get("billTotalMoney"), billEntityRoot.get("billTotalSaleMoney"),
                        billEntityRoot.get("billIsPaid"), billEntityRoot.get("billDepartureDate"))
                .orderBy(criteriaBuilder.asc(billEntityRoot.get("billId")));;
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getBillAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(billEntityRoot.get("billId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public Object[] getBillAsJson(Integer billId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(billEntityRoot.get("accId").as(Integer.class),
                                accountEntityRoot.get("accId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("paytId").as(Integer.class),
                                paymentTypeEntityRoot.get("paytId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("billId").as(Integer.class), billId))
                .multiselect(
                        accountEntityRoot.get("accId"), accountEntityRoot.get("accUsername"),
                        accountEntityRoot.get("accFirstName"), accountEntityRoot.get("accLastName"),
                        accountEntityRoot.get("accIdCard"), accountEntityRoot.get("accPhoneNumber"),
                        accountEntityRoot.get("accDateOfBirth"), paymentTypeEntityRoot.get("paytId"),
                        paymentTypeEntityRoot.get("paytName"), paymentTypeEntityRoot.get("paytSlug"),
                        billEntityRoot.get("billId"), billEntityRoot.get("billCreatedDate"),
                        billEntityRoot.get("billTotalMoney"), billEntityRoot.get("billTotalSaleMoney"),
                        billEntityRoot.get("billShipDate"), billEntityRoot.get("billShipAddress"),
                        billEntityRoot.get("billShipDistrict"), billEntityRoot.get("billShipCity"),
                        billEntityRoot.get("billIsPaid"), billEntityRoot.get("billDepartureDate"));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public Integer createBill(BillEntity bill) {
        return (Integer) Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(bill);
    }

    @Override
    public BillEntity getBillAsObj(Integer billId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BillEntity> criteriaQuery= criteriaBuilder.createQuery(BillEntity.class);
        Root<BillEntity> root = criteriaQuery.from(BillEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("billId").as(Integer.class), billId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }


    @Override
    public boolean updateBill(BillEntity billEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(billEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
