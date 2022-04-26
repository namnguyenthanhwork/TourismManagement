package com.ou.common.services.impl;

import com.ou.common.repositories.CMBillRepository;
import com.ou.common.services.CMBillService;
import com.ou.configs.BeanFactoryConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMBillServiceImpl implements CMBillService {

    @Autowired
    private CMBillRepository cMBillRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getBills(Integer pageIndex) {
        List<Object[]> bills = cMBillRepository.getBills(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        bills.forEach(bill -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("accId", bill[0]);
            jsonObject.put("accUsername", bill[1]);
            jsonObject.put("accFirstName", bill[2]);
            jsonObject.put("accLastName", bill[3]);
            jsonObject.put("accIdCard", bill[4]);
            jsonObject.put("accPhoneNumber", bill[5]);
            jsonObject.put("accDateOfBirth", bill[6]);
            jsonObject.put("paytId", bill[7]);
            jsonObject.put("paytName", bill[8]);
            jsonObject.put("paytSlug", bill[9]);
            jsonObject.put("postId", bill[10]);
            jsonObject.put("postTitle", bill[11]);
            jsonObject.put("postSlug", bill[12]);
            jsonObject.put("tourAverageRating", bill[13]);
            jsonObject.put("billId", bill[14]);
            jsonObject.put("billCreatedDate", bill[15]);
            jsonObject.put("billTotalMoney", bill[16]);
            jsonObject.put("billTotalSaleMoney", bill[17]);
            jsonObject.put("billShipDate", bill[18]);
            jsonObject.put("billShipAddress", bill[19]);
            jsonObject.put("billShipDistrict", bill[20]);
            jsonObject.put("billShipCity", bill[21]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }
}
