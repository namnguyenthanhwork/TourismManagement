package com.ou.common.repositories;


import com.ou.pojos.BillEntity;

import java.util.List;

public interface CMBillRepository {
    List<Object[]> getBills(Integer pageIndex);

    long getBillAmount();

    Object[] getBillAsJson(Integer billId);

    Integer createBill(BillEntity bill);

    BillEntity getBillAsObj(Integer billId);
    boolean updateBill(BillEntity billEntity);
}
