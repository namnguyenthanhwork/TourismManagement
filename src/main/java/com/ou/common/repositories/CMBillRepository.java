package com.ou.common.repositories;


import com.ou.pojos.BillEntity;

import java.util.List;

public interface CMBillRepository {
    List<Object[]> getBills(Integer pageIndex);

    BillEntity createBill(BillEntity bill);

    BillEntity getBill(Integer billId);
    boolean updateBill(BillEntity billEntity);
}
