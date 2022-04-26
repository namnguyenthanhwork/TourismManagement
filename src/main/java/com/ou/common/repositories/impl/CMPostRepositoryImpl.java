package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMPostRepository;
import com.ou.pojos.PostEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;

@Repository
@Transactional
public class CMPostRepositoryImpl implements CMPostRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Override
    public PostEntity getPost(String postSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery = criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), postSlug));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public PostEntity getPost(Integer postId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery = criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(postEntityRoot.get("postId").as(Integer.class), postId));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public boolean createPost(PostEntity postEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(postEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updatePost(PostEntity postEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(postEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deletePost(PostEntity postEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(postEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
