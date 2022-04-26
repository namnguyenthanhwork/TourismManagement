package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMAccountRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional
public class CMAccountRepositoryImpl implements CMAccountRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getAccounts(Integer pageIndex,String ... params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        Root<RoleEntity> roleEntityRoot = criteriaQuery.from(RoleEntity.class);

        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(accountEntityRoot.get("roleId").as(Integer.class),
                roleEntityRoot.get("roleId").as(Integer.class)));
        if (params != null && params.length > 0 && params[0]!=null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.concat(
                    criteriaBuilder.concat(accountEntityRoot.get("accLastName").as(String.class), " "),
                    accountEntityRoot.get("accFirstName").as(String.class)), String.format("%%%s%%", params[0].trim())));
        }

        criteriaQuery.where(predicates.toArray(predicates.toArray(utilBeanFactory.getApplicationContext()
                        .getBean("predicateArray", Predicate[].class))))
                .multiselect(accountEntityRoot.get("accId"), accountEntityRoot.get("accUsername"),
                        accountEntityRoot.get("accPassword"), accountEntityRoot.get("accFirstName"),
                        accountEntityRoot.get("accLastName"), accountEntityRoot.get("accSex"),
                        accountEntityRoot.get("accIdCard"), accountEntityRoot.get("accPhoneNumber"),
                        accountEntityRoot.get("accDateOfBirth"), accountEntityRoot.get("accJoinedDate"),
                        accountEntityRoot.get("accAvatar"), accountEntityRoot.get("accLastAccess"),
                        roleEntityRoot.get("roleSlug"), roleEntityRoot.get("roleName"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public AccountEntity getAccount(String username) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> criteriaQuery = criteriaBuilder.createQuery(AccountEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(accountEntityRoot.get("accUsername").as(String.class), username));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createAccount(AccountEntity accountEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(accountEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateAccount(AccountEntity accountEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(accountEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(AccountEntity accountEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(accountEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
