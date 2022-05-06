package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMPostCommentRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.PostCommentEntity;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMPostCommentRepositoryImpl implements CMPostCommentRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;
    @Override
    public List<PostCommentEntity> getPostCommentByAccount(String accUsername) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PostCommentEntity> postCommentEntityRoot = criteriaQuery.from(PostCommentEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(postCommentEntityRoot.get("accId").as(Integer.class),
                        accountEntityRoot.get("accId").as(Integer.class)),
                criteriaBuilder.equal(accountEntityRoot.get("accUsername").as(String.class),accUsername))
                .multiselect(
                        postCommentEntityRoot.get("cmtId"), postCommentEntityRoot.get("postId"),
                        postCommentEntityRoot.get("accId"), postCommentEntityRoot.get("cmtCreatedDate"),
                        postCommentEntityRoot.get("cmtContent"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<PostCommentEntity> postComments = pojoBeanFactory.getApplicationContext().getBean("postCommentEntityList", List.class);
        results.forEach(result -> {
            PostCommentEntity postComment = pojoBeanFactory.getApplicationContext().getBean(PostCommentEntity.class);
            postComment.setCmtId((Integer) result[0]);
            postComment.setPostId((Integer) result[1]);
            postComment.setAccId((Integer) result[2]);
            postComment.setCmtCreatedDate((Timestamp) result[3]);
            postComment.setCmtContent((String) result[4]);
            postComments.add(postComment);
        });
        return postComments;
    }

    @Override
    public List<PostCommentEntity> getPostCommentByPost(String postSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<PostCommentEntity> postCommentEntityRoot = criteriaQuery.from(PostCommentEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(postCommentEntityRoot.get("postId").as(Integer.class),
                        postEntityRoot.get("postId").as(Integer.class)),
                criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class),postSlug))
                .multiselect(
                        postCommentEntityRoot.get("cmtId"), postCommentEntityRoot.get("postId"),
                        postCommentEntityRoot.get("accId"), postCommentEntityRoot.get("cmtCreatedDate"),
                        postCommentEntityRoot.get("cmtContent"))
                .orderBy(criteriaBuilder.desc(postCommentEntityRoot.get("cmtCreatedDate")));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<PostCommentEntity> postComments = pojoBeanFactory.getApplicationContext().getBean("postCommentEntityList", List.class);
        results.forEach(result -> {
            PostCommentEntity postComment = pojoBeanFactory.getApplicationContext().getBean(PostCommentEntity.class);
            postComment.setCmtId((Integer) result[0]);
            postComment.setPostId((Integer) result[1]);
            postComment.setAccId((Integer) result[2]);
            postComment.setCmtCreatedDate((Timestamp) result[3]);
            postComment.setCmtContent((String) result[4]);
            postComments.add(postComment);
        });
        return postComments;
    }

    @Override
    public boolean createPostComment(PostCommentEntity postCommentEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(postCommentEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updatePostComment(PostCommentEntity postCommentEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(postCommentEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deletePostComment(PostCommentEntity postCommentEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(postCommentEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }


}
