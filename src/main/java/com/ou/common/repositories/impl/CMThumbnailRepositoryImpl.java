package com.ou.common.repositories.impl;


import com.ou.common.repositories.CMThumbnailRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.ThumbnailEntity;
import com.ou.pojos.TourEntity;
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
public class CMThumbnailRepositoryImpl implements CMThumbnailRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getThumbnails(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<ThumbnailEntity> thumbnailEntityRoot = criteriaQuery.from(ThumbnailEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(thumbnailEntityRoot.get("thumId").as(Integer.class),
                                tourEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)))
                .multiselect(
                        thumbnailEntityRoot.get("thumId"), thumbnailEntityRoot.get("thumImage"),
                        postEntityRoot.get("postId"), postEntityRoot.get("postTitle")
                );
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public ThumbnailEntity getThumbnail(Integer thumId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThumbnailEntity> criteriaQuery = criteriaBuilder.createQuery(ThumbnailEntity.class);
        Root<ThumbnailEntity> thumbnailEntityRoot = criteriaQuery.from(ThumbnailEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(thumbnailEntityRoot.get("thumId").as(Integer.class), thumId));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    @Override
    public List<ThumbnailEntity> getThumbnailsByTourId(Integer tourId) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThumbnailEntity> criteriaQuery = criteriaBuilder.createQuery(ThumbnailEntity.class);
        Root<ThumbnailEntity> thumbnailEntityRoot = criteriaQuery.from(ThumbnailEntity.class);
        criteriaQuery.where(criteriaBuilder.equal(thumbnailEntityRoot.get("tourId").as(Integer.class), tourId));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public boolean createThumbnail(ThumbnailEntity thumbnailEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().save(thumbnailEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean updateThumbnail(ThumbnailEntity thumbnailEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().update(thumbnailEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public boolean deleteThumbnail(ThumbnailEntity thumbnailEntity) {
        try {
            Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession().delete(thumbnailEntity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
