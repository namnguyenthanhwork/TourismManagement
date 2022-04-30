package com.ou.common.services.impl;

import com.ou.common.repositories.CMPaymentTypeRepository;
import com.ou.common.services.CMPaymentTypeService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PaymentTypeEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMPaymentTypeServiceImpl implements CMPaymentTypeService {
    @Autowired
    private CMPaymentTypeRepository cMPaymentTypeRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getPaymentTypes(Integer pageIndex) {
        List<Object[]> paymentTypes = cMPaymentTypeRepository.getPaymentTypes(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        paymentTypes.forEach(paymentType -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("paytId", paymentType[0]);
            jsonObject.put("paytName", paymentType[1]);
            jsonObject.put("paytSlug", paymentType[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public PaymentTypeEntity getPaymentTypeAsObj(String paytSlug) {
        if (paytSlug == null || paytSlug.trim().length() == 0)
            return null;
        return cMPaymentTypeRepository.getPaymentType(paytSlug);
    }

    @Override
    public JSONObject getPaymentTypeAsJsonObj(String paytSlug) {
        if (paytSlug == null || paytSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PaymentTypeEntity paymentType = cMPaymentTypeRepository.getPaymentType(paytSlug.trim());
        if (paymentType == null)
            return null;
        jsonObject.put("paytId", paymentType.getPaytId());
        jsonObject.put("paytName", paymentType.getPaytName());
        jsonObject.put("paytSlug", paymentType.getPaytSlug());
        return jsonObject;
    }

    @Override
    public boolean createPaymentType(PaymentTypeEntity paymentTypeEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(paymentTypeEntity.getPaytName());
        paymentTypeEntity.setPaytSlug(slugUtil.getSlug());
        if (cMPaymentTypeRepository.getPaymentType(paymentTypeEntity.getPaytSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult =  cMPaymentTypeRepository.createPaymentType(paymentTypeEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updatePaymentType(PaymentTypeEntity paymentTypeEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(paymentTypeEntity.getPaytName());
        paymentTypeEntity.setPaytSlug(slugUtil.getSlug());
        PaymentTypeEntity updatePaymentType = cMPaymentTypeRepository.getPaymentType(paymentTypeEntity.getPaytSlug());
        if (updatePaymentType != null && updatePaymentType.getPaytId()!=paymentTypeEntity.getPaytId())
            return false;
        boolean updateResult;
        try {
            updateResult = cMPaymentTypeRepository.updatePaymentType(paymentTypeEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deletePaymentType(PaymentTypeEntity paymentTypeEntity) {
        return cMPaymentTypeRepository.deletePaymentType(paymentTypeEntity);
    }
}
