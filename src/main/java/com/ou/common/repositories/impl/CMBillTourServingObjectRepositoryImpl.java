package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMBillTourServingObjectRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.BillEntity;
import com.ou.pojos.BillTourServingObjectEntity;
import com.ou.pojos.TourServingObjectEntity;
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
public class CMBillTourServingObjectRepositoryImpl implements CMBillTourServingObjectRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByBill(Integer billId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillTourServingObjectEntity> billTourServingObjectEntityRoot =
                criteriaQuery.from(BillTourServingObjectEntity.class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(billTourServingObjectEntityRoot.get("billId").as(Integer.class),
                                billEntityRoot.get("billId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("billId").as(Integer.class),billId))
                .multiselect(
                        billTourServingObjectEntityRoot.get("billId"), billTourServingObjectEntityRoot.get("tsvoId"),
                        billTourServingObjectEntityRoot.get("tourAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<BillTourServingObjectEntity> billTourServingObjects = pojoBeanFactory.getApplicationContext()
                .getBean("billTourServingObjectEntities", List.class);
        results.forEach(result -> {
            BillTourServingObjectEntity billTourServingObject =
                    pojoBeanFactory.getApplicationContext().getBean(BillTourServingObjectEntity.class);
            billTourServingObject.setBillId((Integer) result[0]);
            billTourServingObject.setTsvoId((Integer) result[1]);
            billTourServingObject.setTourAmount((Integer) result[2]);
            billTourServingObjects.add(billTourServingObject);
        });
        return billTourServingObjects;
    }

    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByTourServingObject(Integer tsvoId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillTourServingObjectEntity> billTourServingObjectEntityRoot =
                criteriaQuery.from(BillTourServingObjectEntity.class);
        Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(billTourServingObjectEntityRoot.get("tsvoId").as(Integer.class),
                                tourServingObjectEntityRoot.get("tsvoId").as(Integer.class)),
                        criteriaBuilder.equal(tourServingObjectEntityRoot.get("tsvoId").as(Integer.class),tsvoId))
                .multiselect(
                        billTourServingObjectEntityRoot.get("billId"), billTourServingObjectEntityRoot.get("tsvoId"),
                        billTourServingObjectEntityRoot.get("tourAmount"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<BillTourServingObjectEntity> billTourServingObjects = pojoBeanFactory.getApplicationContext()
                .getBean("billTourServingObjectEntities", List.class);
        results.forEach(result -> {
            BillTourServingObjectEntity billTourServingObject =
                    pojoBeanFactory.getApplicationContext().getBean(BillTourServingObjectEntity.class);
            billTourServingObject.setBillId((Integer) result[0]);
            billTourServingObject.setTsvoId((Integer) result[1]);
            billTourServingObject.setTourAmount((Integer) result[2]);
            billTourServingObjects.add(billTourServingObject);
        });
        return billTourServingObjects;
    }

    @Override
    public boolean createBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(billTourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(billTourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(billTourServingObjectEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
