package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMNewsLikeRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
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
public class CMNewsLikeRepositoryImpl implements CMNewsLikeRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;
    @Override
    public List<NewsLikeEntity> getNewsLikeByAccount(String accUsername) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<NewsLikeEntity> newsLikeEntityRoot = criteriaQuery.from(NewsLikeEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(newsLikeEntityRoot.get("accId").as(Integer.class),
                                accountEntityRoot.get("accId").as(Integer.class)),
                        criteriaBuilder.equal(accountEntityRoot.get("accUsername").as(String.class), accUsername))
                .multiselect(newsLikeEntityRoot.get("newsId"), newsLikeEntityRoot.get("accId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<NewsLikeEntity> newsLikeEntities = pojoBeanFactory.getApplicationContext().getBean("newsLikeEntityList", List.class);
        results.forEach(result -> {
            NewsLikeEntity newsLike = pojoBeanFactory.getApplicationContext().getBean(NewsLikeEntity.class);
            newsLike.setNewsId((Integer) result[0]);
            newsLike.setAccId((Integer) result[1]);
            newsLikeEntities.add(newsLike);
        });
        return newsLikeEntities;
    }

    @Override
    public List<NewsLikeEntity> getNewsLikeByNews(String newsSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<NewsLikeEntity> newsLikeEntityRoot = criteriaQuery.from(NewsLikeEntity.class);
        Root<NewsEntity> newsEntityRoot = criteriaQuery.from(NewsEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(newsLikeEntityRoot.get("newsId").as(Integer.class),
                                newsEntityRoot.get("newsId").as(Integer.class)),
                        criteriaBuilder.equal(newsEntityRoot.get("newsId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)),
                        criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), newsSlug))
                .multiselect(newsEntityRoot.get("newsId"), newsLikeEntityRoot.get("accId"));
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        List<NewsLikeEntity> newsLikeEntities = pojoBeanFactory.getApplicationContext().getBean("newsLikeEntityList", List.class);
        results.forEach(result -> {
            NewsLikeEntity newsLike = pojoBeanFactory.getApplicationContext().getBean(NewsLikeEntity.class);
            newsLike.setNewsId((Integer) result[0]);
            newsLike.setAccId((Integer) result[1]);
            newsLikeEntities.add(newsLike);
        });
        return newsLikeEntities;
    }

    @Override
    public NewsLikeEntity getNewsLike(Integer newsId, Integer accId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<NewsLikeEntity> criteriaQuery = criteriaBuilder.createQuery(NewsLikeEntity.class);
        Root<NewsLikeEntity> newsLikeEntityRoot = criteriaQuery.from(NewsLikeEntity.class);
        criteriaQuery.where(
                criteriaBuilder.equal(newsLikeEntityRoot.get("newsId").as(Integer.class), newsId),
                criteriaBuilder.equal(newsLikeEntityRoot.get("accId").as(Integer.class), accId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public boolean createNewsLike(NewsLikeEntity newsLikeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(newsLikeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateNewsLike(NewsLikeEntity newsLikeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(newsLikeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteNewsLike(NewsLikeEntity newsLikeEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(newsLikeEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
