package com.ou.common.services;

import com.ou.pojos.BillTourServingObjectEntity;

import java.util.List;

public interface CMBillTourServingObjectService {
    List<BillTourServingObjectEntity> getBillTourServingObjectByBill(Integer billId);
    List<BillTourServingObjectEntity> getBillTourServingObjectByTourServingObject(Integer tsvoId);

    boolean createBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

    boolean updateBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

    boolean deleteBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

}
