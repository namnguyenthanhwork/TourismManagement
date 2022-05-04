package com.ou.customer.repositories.impl;


import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.repositories.CHomePageRepository;
import com.ou.pojos.CategoryEntity;
import com.ou.pojos.PostEntity;
import com.ou.pojos.TourEntity;
import com.ou.utils.PageUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
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
}
