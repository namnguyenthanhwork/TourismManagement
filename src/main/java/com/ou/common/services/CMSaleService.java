package com.ou.common.services;

import com.ou.pojos.SaleEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMSaleService {
    JSONArray getSales(Integer pageIndex);

    SaleEntity getSaleAsObj(Integer saleId);

    JSONObject getSaleAsJsonObj(Integer saleId);

    boolean createSale(SaleEntity saleEntity);

    boolean updateSale(SaleEntity saleEntity);

    boolean deleteSale(SaleEntity saleEntity);
}
