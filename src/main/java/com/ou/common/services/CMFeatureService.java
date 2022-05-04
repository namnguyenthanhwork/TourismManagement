package com.ou.common.services;

import com.ou.pojos.FeatureEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMFeatureService {
    JSONArray getFeatures(Integer pageIndex);

    long getFeatureAmount();
    FeatureEntity getFeatureAsObj(String feaSlug);

    JSONObject getFeatureAsJsonObj(String feaSlug);

    boolean createFeature(FeatureEntity featureEntity);

    boolean updateFeature(FeatureEntity featureEntity);

    boolean deleteFeature(FeatureEntity featureEntity);
}
