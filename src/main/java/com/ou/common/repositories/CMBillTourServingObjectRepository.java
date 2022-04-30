package com.ou.common.repositories;



import com.ou.pojos.BillTourServingObjectEntity;

import java.util.List;

public interface CMBillTourServingObjectRepository {
    List<BillTourServingObjectEntity> getBillTourServingObjectByBill(Integer billId);
    List<BillTourServingObjectEntity> getBillTourServingObjectByTourServingObject(Integer tsvoId);

    boolean createBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

    boolean updateBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

    boolean deleteBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity);

}
