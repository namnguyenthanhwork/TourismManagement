package com.ou.common.services.impl;

import com.ou.common.repositories.CMFeatureRepository;
import com.ou.common.services.CMFeatureService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.FeatureEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMFeatureServiceImpl implements CMFeatureService {

    @Autowired
    private CMFeatureRepository cMFeatureRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getFeatures(Integer pageIndex) {
        List<Object[]> features = cMFeatureRepository.getFeatures(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        features.forEach(feature -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("feaId", feature[0]);
            jsonObject.put("feaName", feature[1]);
            jsonObject.put("feaSlug", feature[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public FeatureEntity getFeatureAsObj(String feaSlug) {
        if (feaSlug == null || feaSlug.trim().length() == 0)
            return null;
        return cMFeatureRepository.getFeature(feaSlug);
    }

    @Override
    public JSONObject getFeatureAsJsonObj(String feaSlug) {
        if (feaSlug == null || feaSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        FeatureEntity feature = cMFeatureRepository.getFeature(feaSlug.trim());
        if (feature == null)
            return null;
        jsonObject.put("feaId", feature.getFeaId());
        jsonObject.put("feaName", feature.getFeaName());
        jsonObject.put("feaSlug", feature.getFeaSlug());
        return jsonObject;
    }

    @Override
    public boolean createFeature(FeatureEntity featureEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(featureEntity.getFeaName());
        featureEntity.setFeaSlug(slugUtil.getSlug());
        if (cMFeatureRepository.getFeature(featureEntity.getFeaSlug()) != null)
            return false;
        boolean addResult;
        try {
            addResult =  cMFeatureRepository.createFeature(featureEntity);
        } catch (Exception e) {
            addResult = false;
        }
        return addResult;
    }

    @Override
    public boolean updateFeature(FeatureEntity featureEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(featureEntity.getFeaName());
        featureEntity.setFeaSlug(slugUtil.getSlug());
        if (cMFeatureRepository.getFeature(featureEntity.getFeaSlug()) != null)
            return false;
        boolean updateResult;
        try {
            updateResult = cMFeatureRepository.updateFeature(featureEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteFeature(FeatureEntity featureEntity) {
        return cMFeatureRepository.deleteFeature(featureEntity);
    }
}
