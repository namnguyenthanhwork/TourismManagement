package com.ou.common.services;

import com.ou.pojos.PaymentTypeEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMPaymentTypeService {
    JSONArray getPaymentTypes(Integer pageIndex);
    long getPaymentTypeAmount();

    PaymentTypeEntity getPaymentTypeAsObj(String paytSlug);

    JSONObject getPaymentTypeAsJsonObj(String paytSlug);

    boolean createPaymentType(PaymentTypeEntity paymentTypeEntity);

    boolean updatePaymentType(PaymentTypeEntity paymentTypeEntity);

    boolean deletePaymentType(PaymentTypeEntity paymentTypeEntity);
}
