package com.ou.common.repositories;


import com.ou.pojos.FeatureEntity;

import java.util.List;

public interface CMFeatureRepository {
    List<Object[]> getFeatures(Integer pageIndex);
    long getFeatureAmount();

    FeatureEntity getFeature(String feaSlug);

    FeatureEntity getFeature(Integer feaId);

    boolean createFeature(FeatureEntity featureEntity);

    boolean updateFeature(FeatureEntity featureEntity);

    boolean deleteFeature(FeatureEntity featureEntity);
}
