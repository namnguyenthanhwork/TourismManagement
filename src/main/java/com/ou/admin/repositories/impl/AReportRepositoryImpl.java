package com.ou.admin.repositories.impl;

import com.ou.admin.repositories.AReportRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.BillEntity;
import com.ou.pojos.BillTourServingObjectEntity;
import com.ou.utils.PageUtil;
import com.ou.utils.TimeUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class AReportRepositoryImpl implements AReportRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> reportRevenue(TimeUtil timeUtil, Integer pageIndex, Integer... times) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Integer firstTime = null;
        Integer secondTime = null;
        if (times.length == 2) {
            firstTime = times[0];
            secondTime = times[1];
            if (firstTime == null)
                firstTime = 1;
            if (secondTime == null)
                secondTime = 1;

        }
        switch (timeUtil) {
            case MONTH -> criteriaQuery.where(
                    criteriaBuilder.equal(criteriaBuilder.function("MONTH", Integer.class,
                            billEntityRoot.get("billCreatedDate")), firstTime),
                    criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                            billEntityRoot.get("billCreatedDate")), secondTime),
                    criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true));

            case QUARTER -> criteriaQuery.where(
                    criteriaBuilder.equal(criteriaBuilder.function("QUARTER", Integer.class,
                            billEntityRoot.get("billCreatedDate")), firstTime),
                    criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                            billEntityRoot.get("billCreatedDate")), secondTime),
                    criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true));
            case YEAR -> criteriaQuery.where(
                    criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                    billEntityRoot.get("billCreatedDate")), firstTime),
                    criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true));
        }
        criteriaQuery.multiselect(billEntityRoot.get("billCreatedDate"),
                        criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                        criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                .groupBy(billEntityRoot.get("billCreatedDate"))
                .orderBy(criteriaBuilder.asc(billEntityRoot.get("billCreatedDate")));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> reportTourAmount(TimeUtil timeUtil, Integer pageIndex, Integer... times) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Root<BillTourServingObjectEntity> billTourServingObjectEntityRoot =
                criteriaQuery.from(BillTourServingObjectEntity.class);
        Integer firstTime = null;
        Integer secondTime = null;
        if (times.length == 2) {
            firstTime = times[0];
            secondTime = times[1];
            if (firstTime == null)
                firstTime = 1;
            if (secondTime == null)
                secondTime = 1;

        }
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(billEntityRoot.get("billId").as(Integer.class),
                billTourServingObjectEntityRoot.get("billId").as(Integer.class)));
        predicates.add(criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true));
        switch (timeUtil) {
            case MONTH -> {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("MONTH", Integer.class,
                        billEntityRoot.get("billCreatedDate")), firstTime));
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                        billEntityRoot.get("billCreatedDate")), secondTime));

            }
            case QUARTER -> {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("QUARTER", Integer.class,
                        billEntityRoot.get("billCreatedDate")), firstTime));
                predicates.add(criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                        billEntityRoot.get("billCreatedDate")), secondTime));

            }
            case YEAR -> predicates.add(criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class,
                    billEntityRoot.get("billCreatedDate")), firstTime));
        }

        criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext().getBean("predicateArray",
                        Predicate[].class)))
                .multiselect(
                        billEntityRoot.get("billCreatedDate"),
                        criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                .groupBy(billEntityRoot.get("billCreatedDate"))
                .orderBy(criteriaBuilder.asc(billEntityRoot.get("billCreatedDate")));

        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }
}
