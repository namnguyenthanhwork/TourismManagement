package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMUserRepository;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

@Repository
public class CMUserRepositoryImpl implements CMUserRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public AccountEntity getAccountByUsername(String accUsername) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> criteriaQuery = criteriaBuilder.createQuery(AccountEntity.class);
        Root<AccountEntity> root = criteriaQuery.from(AccountEntity.class);
        criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.equal(root.get("accUsername").as(String.class), accUsername);
        return session.createQuery(criteriaQuery.where(predicate)).getSingleResult();
    }

    @Override
    public String getRoleSlugOfAccount(String accUsername) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        Root<RoleEntity> roleEntityRoot = criteriaQuery.from(RoleEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(accountEntityRoot.get("roleId"), roleEntityRoot.get("roleId")),
                        criteriaBuilder.equal(accountEntityRoot.get("accUsername").as(String.class), accUsername))
                .multiselect(roleEntityRoot.get("roleSlug"))
                .groupBy(roleEntityRoot.get("roleSlug"));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
