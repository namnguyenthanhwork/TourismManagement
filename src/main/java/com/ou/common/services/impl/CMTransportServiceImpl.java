package com.ou.common.services.impl;

import com.ou.common.repositories.CMTransportRepository;
import com.ou.common.services.CMTransportService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.TransportEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMTransportServiceImpl implements CMTransportService {


    @Autowired
    private CMTransportRepository cMTransportRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getTransports(Integer pageIndex) {
        List<Object[]> transports = cMTransportRepository.getTransports(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        transports.forEach(transport -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("tranId", transport[0]);
            jsonObject.put("tranName", transport[1]);
            jsonObject.put("tranSlug", transport[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public TransportEntity getTransportAsObj(String tranSlug) {
        if (tranSlug == null || tranSlug.trim().length() == 0)
            return null;
        return cMTransportRepository.getTransport(tranSlug);
    }

    @Override
    public JSONObject getTransportAsJsonObj(String tranSlug) {
        if (tranSlug == null || tranSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        TransportEntity transport = cMTransportRepository.getTransport(tranSlug.trim());
        if (transport == null)
            return null;
        jsonObject.put("tranId", transport.getTranId());
        jsonObject.put("tranName", transport.getTranName());
        jsonObject.put("tranSlug", transport.getTranSlug());
        return jsonObject;
    }

    @Override
    public boolean createTransport(TransportEntity transportEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(transportEntity.getTranName());
        transportEntity.setTranSlug(slugUtil.getSlug());
        if (cMTransportRepository.getTransport(transportEntity.getTranSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult = cMTransportRepository.createTransport(transportEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateTransport(TransportEntity transportEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(transportEntity.getTranName());
        transportEntity.setTranSlug(slugUtil.getSlug());
        TransportEntity updateTransport = cMTransportRepository.getTransport(transportEntity.getTranSlug());
        if (updateTransport != null && updateTransport.getTranId()!=transportEntity.getTranId())
            return false;
        boolean updateResult;
        try {
            updateResult = cMTransportRepository.updateTransport(transportEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteTransport(TransportEntity transportEntity) {
        return cMTransportRepository.deleteTransport(transportEntity);
    }
}
