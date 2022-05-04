package com.ou.common.services.impl;

import com.ou.common.repositories.CMServingObjectRepository;
import com.ou.common.services.CMServingObjectService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ServingObjectEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMServingObjectServiceImpl implements CMServingObjectService {
    @Autowired
    private CMServingObjectRepository cMServingObjectRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getServingObjects(Integer pageIndex) {
        List<Object[]> servingObjects = cMServingObjectRepository.getServingObjects(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        servingObjects.forEach(servingObject -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("svoId", servingObject[0]);
            jsonObject.put("svoName", servingObject[1]);
            jsonObject.put("svoSlug", servingObject[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getServingObjectAmount() {
        return cMServingObjectRepository.getServingObjectAmount();
    }

    @Override
    public ServingObjectEntity getServingObjectAsObj(String svoSlug) {
        if (svoSlug == null || svoSlug.trim().length() == 0)
            return null;
        return cMServingObjectRepository.getServingObject(svoSlug);
    }

    @Override
    public ServingObjectEntity getServingObject(Integer svoId) {
        if (svoId == null )
            return null;
        return cMServingObjectRepository.getServingObject(svoId);
    }

    @Override
    public JSONObject getServingObjectAsJsonObj(String svoSlug) {
        if (svoSlug == null || svoSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        ServingObjectEntity servingObject = cMServingObjectRepository.getServingObject(svoSlug.trim());
        if (servingObject == null)
            return null;
        jsonObject.put("svoId", servingObject.getSvoId());
        jsonObject.put("svoName", servingObject.getSvoName());
        jsonObject.put("svoSlug", servingObject.getSvoSlug());
        return jsonObject;
    }

    @Override
    public boolean createServingObject(ServingObjectEntity servingObjectEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(servingObjectEntity.getSvoName());
        servingObjectEntity.setSvoSlug(slugUtil.getSlug());
        if (cMServingObjectRepository.getServingObject(servingObjectEntity.getSvoSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult = cMServingObjectRepository.createServingObject(servingObjectEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateServingObject(ServingObjectEntity servingObjectEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(servingObjectEntity.getSvoName());
        servingObjectEntity.setSvoSlug(slugUtil.getSlug());
        ServingObjectEntity updateServingObject = cMServingObjectRepository.
                getServingObject(servingObjectEntity.getSvoSlug());
        if (updateServingObject!= null && updateServingObject.getSvoId()!=servingObjectEntity.getSvoId())
            return false;
        boolean updateResult;
        try {
            updateResult =  cMServingObjectRepository.updateServingObject(servingObjectEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteServingObject(ServingObjectEntity servingObjectEntity) {
        return cMServingObjectRepository.deleteServingObject(servingObjectEntity);
    }
}
