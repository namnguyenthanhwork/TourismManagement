package com.ou.common.services.impl;


import com.ou.common.services.CMBillTourServingObjectService;
import com.ou.pojos.BillTourServingObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMBillTourServingObjectServiceImpl implements CMBillTourServingObjectService {

    @Autowired
    private CMBillTourServingObjectService cmBillTourServingObjectService;


    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByBill(Integer billId) {
        if (billId == null )
            return null;
        return cmBillTourServingObjectService.getBillTourServingObjectByBill(billId);
    }

    @Override
    public List<BillTourServingObjectEntity> getBillTourServingObjectByTourServingObject(Integer tsvoId) {
        if (tsvoId == null )
            return null;
        return cmBillTourServingObjectService.getBillTourServingObjectByTourServingObject(tsvoId);
    }

    @Override
    public boolean createBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectService.createBillTourServingObject(billTourServingObjectEntity);
    }

    @Override
    public boolean updateBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectService.updateBillTourServingObject(billTourServingObjectEntity);
    }

    @Override
    public boolean deleteBillTourServingObject(BillTourServingObjectEntity billTourServingObjectEntity) {
        return cmBillTourServingObjectService.deleteBillTourServingObject(billTourServingObjectEntity);

    }
}
