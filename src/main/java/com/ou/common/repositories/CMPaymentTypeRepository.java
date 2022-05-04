package com.ou.common.repositories;


import com.ou.pojos.PaymentTypeEntity;

import java.util.List;

public interface CMPaymentTypeRepository {
    List<Object[]> getPaymentTypes(Integer pageIndex);

    PaymentTypeEntity getPaymentType(String paytSlug);
    long getPaymentTypeAmount();

    PaymentTypeEntity getPaymentType(Integer paytId);

    boolean createPaymentType(PaymentTypeEntity paymentTypeEntity);

    boolean updatePaymentType(PaymentTypeEntity paymentTypeEntity);

    boolean deletePaymentType(PaymentTypeEntity paymentTypeEntity);
}
