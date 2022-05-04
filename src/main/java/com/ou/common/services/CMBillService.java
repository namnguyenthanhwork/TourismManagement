package com.ou.common.services;

import com.ou.pojos.BillEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMBillService {
    JSONArray getBills(Integer pageIndex);
    long getBillAmount();
    BillEntity getBillAsObj(Integer billId);
    JSONObject getBillAsJson(Integer billId);
    BillEntity createBill(BillEntity bill);

    boolean updateBill(BillEntity billEntity);
}
