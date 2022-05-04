package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMServiceRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ScheduleEntity;
import com.ou.pojos.ServiceEntity;
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
public class CMServiceRepositoryImpl implements CMServiceRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getServices(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ServiceEntity> serviceEntityRoot = criteriaQuery.from(ServiceEntity.class);
        criteriaQuery.multiselect(serviceEntityRoot.get("servId"), serviceEntityRoot.get("servTitle"),
                serviceEntityRoot.get("servSlug"), serviceEntityRoot.get("servContent"))
                .orderBy(criteriaBuilder.asc(serviceEntityRoot.get("servId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ServiceEntity getService(String servSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceEntity> criteriaQuery = criteriaBuilder.createQuery(ServiceEntity.class);
        Root<ServiceEntity> serviceEntityRoot = criteriaQuery.from(ServiceEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(serviceEntityRoot.get("servSlug").as(String.class), servSlug));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public long getServiceAmount() {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<ServiceEntity> serviceEntityRoot = criteriaQuery.from(ServiceEntity.class);
        criteriaQuery.multiselect(criteriaBuilder.count(serviceEntityRoot.get("servId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return 0;
        }
    }

    @Override
    public ServiceEntity getService(Integer servId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceEntity> criteriaQuery = criteriaBuilder.createQuery(ServiceEntity.class);
        Root<ServiceEntity> serviceEntityRoot = criteriaQuery.from(ServiceEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(serviceEntityRoot.get("servId").as(Integer.class), servId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createService(ServiceEntity serviceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(serviceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateService(ServiceEntity serviceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(serviceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteService(ServiceEntity serviceEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(serviceEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
