package com.ou.common.services.impl;

import com.ou.common.repositories.CMServiceRepository;
import com.ou.common.services.CMServiceService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ServiceEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMServiceServiceImpl implements CMServiceService {

    @Autowired
    private CMServiceRepository cMServiceRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getServices(Integer pageIndex) {
        List<Object[]> services = cMServiceRepository.getServices(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        services.forEach(role -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("servId", role[0]);
            jsonObject.put("servTitle", role[1]);
            jsonObject.put("servSlug", role[2]);
            jsonObject.put("servContent", role[3]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public ServiceEntity getServiceAsObj(String servSlug) {
        if (servSlug == null || servSlug.trim().length() == 0)
            return null;
        return cMServiceRepository.getService(servSlug);
    }

    @Override
    public JSONObject getServiceAsJsonObj(String servSlug) {
        if (servSlug == null || servSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        ServiceEntity service = cMServiceRepository.getService(servSlug.trim());
        if (service == null)
            return null;
        jsonObject.put("servId", service.getServId());
        jsonObject.put("servTitle", service.getServTitle());
        jsonObject.put("servSlug", service.getServSlug());
        jsonObject.put("servContent", service.getServContent());
        return jsonObject;
    }

    @Override
    public boolean createService(ServiceEntity serviceEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(serviceEntity.getServTitle());
        serviceEntity.setServSlug(slugUtil.getSlug());
        if (cMServiceRepository.getService(serviceEntity.getServSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult = cMServiceRepository.createService(serviceEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateService(ServiceEntity serviceEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(serviceEntity.getServTitle());
        serviceEntity.setServSlug(slugUtil.getSlug());
        if (cMServiceRepository.getService(serviceEntity.getServSlug()) != null)
            return false;
        boolean updateResult;
        try {
            updateResult = cMServiceRepository.updateService(serviceEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteService(ServiceEntity serviceEntity) {
        return cMServiceRepository.deleteService(serviceEntity);
    }
}
