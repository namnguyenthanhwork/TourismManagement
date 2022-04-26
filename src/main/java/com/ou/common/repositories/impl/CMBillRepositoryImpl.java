package com.ou.common.repositories.impl;

import com.ou.common.repositories.CMBillRepository;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.PageUtil;
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
public class CMBillRepositoryImpl implements CMBillRepository {
    @Autowired
    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<Object[]> getBills(Integer pageIndex) {
        Session session = Objects.requireNonNull(localSessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<BillEntity> billEntityRoot = criteriaQuery.from(BillEntity.class);
        Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);
        Root<PaymentTypeEntity> paymentTypeEntityRoot = criteriaQuery.from(PaymentTypeEntity.class);
        Root<TourEntity> tourEntityRoot = criteriaQuery.from(TourEntity.class);
        Root<PostEntity> postEntityRoot = criteriaQuery.from(PostEntity.class);
        criteriaQuery.where(
                        criteriaBuilder.equal(billEntityRoot.get("accId").as(Integer.class),
                                accountEntityRoot.get("accId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("paytId").as(Integer.class),
                                paymentTypeEntityRoot.get("paytId").as(Integer.class)),
                        criteriaBuilder.equal(billEntityRoot.get("tourId").as(Integer.class),
                                tourEntityRoot.get("tourId").as(Integer.class)),
                        criteriaBuilder.equal(tourEntityRoot.get("tourId").as(Integer.class),
                                postEntityRoot.get("postId").as(Integer.class)))
                .multiselect(
                        accountEntityRoot.get("accId"), accountEntityRoot.get("accUsername"),
                        accountEntityRoot.get("accFirstName"), accountEntityRoot.get("accLastName"),
                        accountEntityRoot.get("accIdCard"), accountEntityRoot.get("accPhoneNumber"),
                        accountEntityRoot.get("accDateOfBirth"), paymentTypeEntityRoot.get("paytId"),
                        paymentTypeEntityRoot.get("paytName"), paymentTypeEntityRoot.get("paytSlug"),
                        postEntityRoot.get("postId"), postEntityRoot.get("postTitle"),
                        postEntityRoot.get("postSlug"), tourEntityRoot.get("tourAverageRating"),
                        billEntityRoot.get("billId"), billEntityRoot.get("billCreatedDate"),
                        billEntityRoot.get("billTotalMoney"), billEntityRoot.get("billTotalSaleMoney"),
                        billEntityRoot.get("billShipDate"), billEntityRoot.get("billShipAddress"),
                        billEntityRoot.get("billShipDistrict"), billEntityRoot.get("billShipCity"));
        if (pageIndex != null) {
            PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
            pageUtil.setSearchIndex(pageIndex);
            return session.createQuery(criteriaQuery).setFirstResult(pageUtil.getSearchIndex())
                    .setMaxResults(PageUtil.getPageSize()).getResultList();
        }
        return session.createQuery(criteriaQuery).getResultList();
    }
}
