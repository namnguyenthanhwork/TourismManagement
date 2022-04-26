package com.ou.common.repositories;


import com.ou.pojos.SaleEntity;

import java.util.List;

public interface CMSaleRepository {
    List<Object[]> getSales(Integer pageIndex);

    SaleEntity getSale(Integer saleId);

    boolean createSale(SaleEntity saleEntity);

    boolean updateSale(SaleEntity saleEntity);

    boolean deleteSale(SaleEntity saleEntity);
}
