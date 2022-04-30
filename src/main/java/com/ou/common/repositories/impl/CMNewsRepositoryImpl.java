package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMNewsRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.NewsEntity;
import com.ou.pojos.PostEntity;
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CMNewsRepositoryImpl implements CMNewsRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Override
    public List<Object[]> getNewsList(Integer pageIndex, String ... params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<NewsEntity> newsEntityRoot = criteriaQuery.from(NewsEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);

        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(newsEntityRoot.get("newsId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)));
        if (params != null && params.length > 0 && params[0] != null) {
            predicates.add(criteriaBuilder.like(postEntityRoot.get("postTitle").as(String.class),
                    String.format("%%%s%%", params[0].trim())));
        }
        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                        .getBean("predicateArray", Predicate[].class)))
                .multiselect(
                        newsEntityRoot.get("newsId"), newsEntityRoot.get("newsCreatedDate"),
                        newsEntityRoot.get("newsLikeAmount"), postEntityRoot.get("postTitle"),
                        postEntityRoot.get("postSlug"), postEntityRoot.get("postContent"),
                        postEntityRoot.get("postCoverPage"));

        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public NewsEntity getNews(String newsSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<NewsEntity> newsEntityRoot = criteriaQuery.from(NewsEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(newsEntityRoot.get("newsId").as(Integer.class),
                        postEntityRoot.get("postId").as(Integer.class)),
                criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class), newsSlug))
                .multiselect( newsEntityRoot.get("newsId"), newsEntityRoot.get("newsCreatedDate"),
                        newsEntityRoot.get("newsLikeAmount"));
        try {
            Object[] result = session.createQuery(criteriaQuery).getSingleResult();
            NewsEntity news = pojoBeanFactory.getApplicationContext().getBean(NewsEntity.class);
            news.setNewsId((Integer) result[0]);
            news.setNewsCreatedDate(Timestamp.valueOf(result[1].toString()));
            news.setNewsLikeAmount((Integer) result[2]);
            return news;
        }catch (NoResultException noResultException){
            return null;
        }

    }

    @Override
    public NewsEntity getNews(Integer newsId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<NewsEntity> criteriaQuery = criteriaBuilder.createQuery(NewsEntity.class);
        Root<NewsEntity> newsEntityRoot = criteriaQuery.from(NewsEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(newsEntityRoot.get("newsId").as(Integer.class), newsId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public boolean createNews(NewsEntity newsEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(newsEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateNews(NewsEntity newsEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(newsEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteNews(NewsEntity newsEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(newsEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
