package com.ou.common.services;

import com.ou.pojos.SalePercentEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMSalePercentService {
    JSONArray getSalePercents(Integer pageIndex);
    long getSalePercentAmount();
    SalePercentEntity getSalePercentAsObj(Integer salePercentId);

    JSONObject getSalePercentAsJsonObj(Integer salePercentId);

    boolean createSalePercent(SalePercentEntity salePercentEntity);

    boolean updateSalePercent(SalePercentEntity salePercentEntity);

    boolean deleteSalePercent(SalePercentEntity salePercentEntity);
}
