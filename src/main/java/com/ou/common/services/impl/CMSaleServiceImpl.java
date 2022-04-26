package com.ou.common.services.impl;

import com.ou.common.repositories.CMSalePercentRepository;
import com.ou.common.repositories.CMSaleRepository;
import com.ou.common.services.CMSaleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.SaleEntity;
import com.ou.pojos.SalePercentEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMSaleServiceImpl implements CMSaleService {

    @Autowired
    private CMSaleRepository cMSaleRepository;

    @Autowired
    private CMSalePercentRepository cMSalePercentRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getSales(Integer pageIndex) {
        List<Object[]> sales = cMSaleRepository.getSales(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        sales.forEach(sale -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("saleId", sale[0]);
            jsonObject.put("saleFromDate", sale[1]);
            jsonObject.put("saleToDate", sale[2]);
            jsonObject.put("sperId", sale[3]);
            jsonObject.put("sperPercent", sale[4]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public SaleEntity getSaleAsObj(Integer saleId) {
        if (saleId == null)
            return null;
        return cMSaleRepository.getSale(saleId);
    }

    @Override
    public JSONObject getSaleAsJsonObj(Integer saleId) {
        if (saleId == null)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        SaleEntity sale = cMSaleRepository.getSale(saleId);
        if (sale == null)
            return null;
        jsonObject.put("saleId", sale.getSaleId());
        jsonObject.put("saleFromDate", sale.getSaleFromDate());
        jsonObject.put("saleToDate", sale.getSaleToDate());
        SalePercentEntity salePercentEntity = cMSalePercentRepository.getSalePercent(sale.getSperId());
        jsonObject.put("sperId", salePercentEntity.getSperId());
        jsonObject.put("sperPercent", salePercentEntity.getSperPercent());
        return jsonObject;
    }

    @Override
    public boolean createSale(SaleEntity saleEntity) {
        return cMSaleRepository.createSale(saleEntity);
    }

    @Override
    public boolean updateSale(SaleEntity saleEntity) {
        return cMSaleRepository.updateSale(saleEntity);
    }

    @Override
    public boolean deleteSale(SaleEntity saleEntity) {
        return cMSaleRepository.deleteSale(saleEntity);
    }
}
