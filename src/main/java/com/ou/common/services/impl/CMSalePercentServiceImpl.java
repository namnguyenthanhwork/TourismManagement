package com.ou.common.services.impl;

import com.ou.common.repositories.CMSalePercentRepository;
import com.ou.common.services.CMSalePercentService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SalePercentEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMSalePercentServiceImpl implements CMSalePercentService {

    @Autowired
    private CMSalePercentRepository cMSalePercentRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getSalePercents(Integer pageIndex) {
        List<Object[]> salePercents = cMSalePercentRepository.getSalePercents(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        salePercents.forEach(salePercent -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("sperId", salePercent[0]);
            jsonObject.put("sperPercent", salePercent[1]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public SalePercentEntity getSalePercentAsObj(Integer salePercentId) {
        if (salePercentId == null)
            return null;
        return cMSalePercentRepository.getSalePercent(salePercentId);
    }

    @Override
    public JSONObject getSalePercentAsJsonObj(Integer salePercentId) {
        if (salePercentId == null)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        SalePercentEntity salePercent = cMSalePercentRepository.getSalePercent(salePercentId);
        if (salePercentId == null)
            return null;
        jsonObject.put("sperId", salePercent.getSperId());
        jsonObject.put("sperPercent", salePercent.getSperPercent());
        return jsonObject;
    }

    @Override
    public boolean createSalePercent(SalePercentEntity salePercentEntity) {
        return cMSalePercentRepository.createSalePercent(salePercentEntity);
    }

    @Override
    public boolean updateSalePercent(SalePercentEntity salePercentEntity) {
        return  cMSalePercentRepository.updateSalePercent(salePercentEntity);
    }

    @Override
    public boolean deleteSalePercent(SalePercentEntity salePercentEntity) {
        return cMSalePercentRepository.deleteSalePercent(salePercentEntity);
    }
}
