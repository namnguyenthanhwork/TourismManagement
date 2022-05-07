package com.ou.customer.repositories.impl;


import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.repositories.CHomePageRepository;
import com.ou.pojos.*;
import com.ou.utils.PageUtil;
import com.ou.utils.TourQueryTypeUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class CHomePageRepositoryImpl implements CHomePageRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getTourByCategory(String catSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        Root<CategoryEntity> categoryEntityRoot = criteriaQuery.from(CategoryEntity.class);
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)));
        predicates.add(criteriaBuilder.equal(tourEntityRoot.get("catId").as(Integer.class),
                categoryEntityRoot.get("catId").as(Integer.class)));
        predicates.add(criteriaBuilder.equal(categoryEntityRoot.get("catSlug").as(String.class), catSlug));
        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                        .getBean("predicateArray", Predicate[].class)))
                .multiselect(postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postCoverPage"), postEntityRoot.get("postId"))
                .orderBy(criteriaBuilder.asc(tourEntityRoot.get("tourId")));
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> getTours(TourQueryTypeUtil tourQueryTypeUtil, Integer pageIndex, String params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)));
        if (tourQueryTypeUtil != null)
            switch (tourQueryTypeUtil) {
                case KEYWORD -> {
                    predicates.add(criteriaBuilder.like(postEntityRoot.get("postTitle").as(String.class),
                            String.format("%%%s%%", params.trim())));
                }
                case PRICE -> {
                    Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
                    predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                            tourServingObjectEntityRoot.get("tourId").as(Integer.class)));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(tourServingObjectEntityRoot.get("tourPrice")
                                    .as(BigDecimal.class),BigDecimal.valueOf(Long.parseLong(params))));
                }
                case SCHEDULE -> {
                    Root<ScheduleEntity> scheduleEntityRoot = criteriaQuery.from(ScheduleEntity.class);
                    predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                            scheduleEntityRoot.get("tourId").as(Integer.class)));
                    predicates.add(criteriaBuilder.like(scheduleEntityRoot.get("scheTitle").as(String.class),
                            String.format("%%%s%%", params.trim())));
                }

            }
        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                        .getBean("predicateArray", Predicate[].class)))
                .multiselect(postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postCoverPage"), postEntityRoot.get("postId"))
                .groupBy(postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postCoverPage"), postEntityRoot.get("postId"),
                        tourEntityRoot.get("tourId"))
                .orderBy(criteriaBuilder.asc(tourEntityRoot.get("tourId")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        List<Object[]> results = session.createQuery(criteriaQuery).getResultList();
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public long getTourAmount(TourQueryTypeUtil tourQueryTypeUtil, String... params) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        if (tourQueryTypeUtil != null)
            switch (tourQueryTypeUtil) {
                case KEYWORD -> {
                    Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
                    criteriaQuery.where(
                            criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                    postEntityRoot.get("postId").as(Integer.class)),
                            criteriaBuilder.like(postEntityRoot.get("postTitle").as(String.class),
                                    String.format("%%%s%%", params[0].trim())));
                }
                case PRICE -> {
                    Root<TourServingObjectEntity> tourServingObjectEntityRoot = criteriaQuery.from(TourServingObjectEntity.class);
                    criteriaQuery.where(
                            criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                    tourServingObjectEntityRoot.get("tourId").as(Integer.class)),
                            criteriaBuilder.lessThanOrEqualTo(tourServingObjectEntityRoot.get("tourPrice")
                                    .as(BigDecimal.class), BigDecimal.valueOf(Long.parseLong(params[0]))));
                }
                case SCHEDULE -> {
                    Root<ScheduleEntity> scheduleEntityRoot = criteriaQuery.from(ScheduleEntity.class);
                    criteriaQuery.where(
                            criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                    scheduleEntityRoot.get("tourId").as(Integer.class)),
                            criteriaBuilder.like(scheduleEntityRoot.get("scheTitle").as(String.class),
                                    String.format("%%%s%%", params[0])));
                }
            }
        criteriaQuery.multiselect(tourEntityRoot.get("tourId")).groupBy(tourEntityRoot.get("tourId"));
        try {
            return session.createQuery(criteriaQuery).getResultList().size();
        } catch (Exception noResultException) {
            return 0;
        }
    }

    @Override
    public Object[] getTour(String tourSlug) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                postEntityRoot.get("postId").as(Integer.class)));
        predicates.add(criteriaBuilder.equal(postEntityRoot.get("postSlug").as(String.class),
                tourSlug));
        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                        .getBean("predicateArray", Predicate[].class)))
                .multiselect(postEntityRoot.get("postTitle"), postEntityRoot.get("postSlug"),
                        postEntityRoot.get("postCoverPage"), postEntityRoot.get("postId"))
                .orderBy(criteriaBuilder.asc(tourEntityRoot.get("tourId")));
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}
