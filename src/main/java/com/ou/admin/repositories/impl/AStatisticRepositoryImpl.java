package com.ou.admin.repositories.impl;

import com.ou.admin.repositories.AStatisticRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.BillEntity;
import com.ou.pojos.BillTourServingObjectEntity;
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
public class AStatisticRepositoryImpl implements AStatisticRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> statisticRevenue(TimeUtil timeUtil, Integer... times) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Integer fromTime = null;
        Integer toTime = null;
        if (times.length >= 2) {
            fromTime = times[0];
            toTime = times[1];
            if (fromTime == null || toTime == null) {
                fromTime = 1;
                toTime = 1;
            }
        }
        switch (timeUtil) {
            case MONTH -> criteriaQuery.where(criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true))
                    .multiselect(
                            criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("MONTH", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case QUARTER -> criteriaQuery.where(criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true))
                    .multiselect(
                            criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("QUARTER", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case YEAR -> criteriaQuery.where(criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true))
                    .multiselect(
                            criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("YEAR", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case MONTH_TO_MONTH -> criteriaQuery.where(
                            criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true),
                            criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                    "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime),
                            criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                    "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), toTime))
                    .multiselect(
                            criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("MONTH", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case QUARTER_TO_QUARTER -> criteriaQuery.where(
                            criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true),
                            criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                    "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime),
                            criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                    "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), toTime))
                    .multiselect(
                            criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("QUARTER", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case YEAR_TO_YEAR -> criteriaQuery.where(
                            criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true),
                            criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                    "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime),
                            criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                    "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), toTime))
                    .multiselect(
                            criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                            criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                    .groupBy(criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("YEAR", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
        }
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> statisticTourAmount(TimeUtil timeUtil, Integer... times) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Root<BillTourServingObjectEntity> billTourServingObjectEntityRoot =
                criteriaQuery.from(BillTourServingObjectEntity.class);
        Integer fromTime = null;
        Integer toTime = null;
        if (times.length >= 2) {
            fromTime = times[0];
            toTime = times[1];
            if (fromTime == null || toTime == null) {
                fromTime = 1;
                toTime = 1;
            }
        }
        List<Predicate> predicates = utilBeanFactory.getApplicationContext().getBean("predicateList", List.class);
        predicates.add(criteriaBuilder.equal(billEntityRoot.get("billId").as(Integer.class),
                billTourServingObjectEntityRoot.get("billId").as(Integer.class)));
        predicates.add(criteriaBuilder.equal(billEntityRoot.get("billIsPaid"), true));
        switch (timeUtil) {
            case MONTH -> criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                            .getBean("predicateArray", Predicate[].class)))
                    .multiselect(
                            criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                    .groupBy(criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("MONTH", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case QUARTER -> criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                            .getBean("predicateArray", Predicate[].class)))
                    .multiselect(
                            criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                    .groupBy(criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("QUARTER", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case YEAR -> criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                            .getBean("predicateArray", Predicate[].class)))
                    .multiselect(
                            criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                            criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                    .groupBy(criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")))
                    .orderBy(criteriaBuilder.asc(criteriaBuilder.function("YEAR", Integer.class,
                            billEntityRoot.get("billCreatedDate"))));
            case MONTH_TO_MONTH -> {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                        "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                        "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), toTime));
                criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                                .getBean("predicateArray", Predicate[].class)))
                        .multiselect(
                                criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                        .groupBy(criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")))
                        .orderBy(criteriaBuilder.asc(criteriaBuilder.function("MONTH", Integer.class,
                                billEntityRoot.get("billCreatedDate"))));

            }
            case QUARTER_TO_QUARTER -> {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                        "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                        "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), toTime));
                criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                                .getBean("predicateArray", Predicate[].class)))
                        .multiselect(
                                criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                        .groupBy(criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")))
                        .orderBy(criteriaBuilder.asc(criteriaBuilder.function("QUARTER", Integer.class,
                                billEntityRoot.get("billCreatedDate"))));
            }
            case YEAR_TO_YEAR -> {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                        "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), fromTime));
                predicates.add(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                        "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), toTime));
                criteriaQuery.where(predicates.toArray(utilBeanFactory.getApplicationContext()
                                .getBean("predicateArray", Predicate[].class)))
                        .multiselect(
                                criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billTourServingObjectEntityRoot.get("tourAmount")))
                        .groupBy(criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")))
                        .orderBy(criteriaBuilder.asc(criteriaBuilder.function("YEAR", Integer.class,
                                billEntityRoot.get("billCreatedDate"))));
            }
        }

        return session.createQuery(criteriaQuery).getResultList();
    }
}
