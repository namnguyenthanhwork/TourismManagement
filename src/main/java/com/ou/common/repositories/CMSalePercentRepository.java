package com.ou.common.repositories;

import com.ou.pojos.SalePercentEntity;

import java.util.List;

public interface CMSalePercentRepository {

    List<Object[]> getSalePercents(Integer pageIndex);
    long getSalePercentAmount();
    SalePercentEntity getSalePercent(Integer salePercentId);

    boolean createSalePercent(SalePercentEntity salePercentEntity);

    boolean updateSalePercent(SalePercentEntity salePercentEntity);

    boolean deleteSalePercent(SalePercentEntity salePercentEntity);
}
