package com.ou.admin.repositories.impl;

import com.ou.admin.repositories.AStatisticRepository;
import com.ou.pojos.BillEntity;
import com.ou.utils.TimeUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class AStatisticRepositoryImpl implements AStatisticRepository {

    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;


    @Override
    public List<Object[]> statisticRevenue(TimeUtil timeUtil, Integer...times) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        switch (timeUtil) {
            case MONTH -> {
                criteriaQuery.multiselect(
                                criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                        .groupBy(criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")));
            }
            case QUARTER -> {
                criteriaQuery.multiselect(
                                criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                        .groupBy(criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")));
            }
            case YEAR -> {
                criteriaQuery.multiselect(
                                criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney")))
                        .groupBy(criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")));
            }
            case MONTH_TO_MONTH -> {
                criteriaQuery.where(
                                criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                        "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), times[0]),
                                criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                        "MONTH", Integer.class, billEntityRoot.get("billCreatedDate")), times[1]))
                        .multiselect(
                                criteriaBuilder.function("MONTH", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney"))
                        );
            }
            case QUARTER_TO_QUARTER -> {
                criteriaQuery.where(
                                criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                        "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), times[0]),
                                criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                        "QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")), times[1]))
                        .multiselect(
                                criteriaBuilder.function("QUARTER", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney"))
                        );
            }
            case YEAR_TO_YEAR -> {
                criteriaQuery.where(
                                criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.function(
                                        "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), times[0]),
                                criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.function(
                                        "YEAR", Integer.class, billEntityRoot.get("billCreatedDate")), times[1]))
                        .multiselect(
                                criteriaBuilder.function("YEAR", Integer.class, billEntityRoot.get("billCreatedDate")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalMoney")),
                                criteriaBuilder.sum(billEntityRoot.get("billTotalSaleMoney"))
                        );
            }
        }

        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Object[]> statisticTourAmount(TimeUtil timeUtil, Integer... times) {
        return null;
    }
}
