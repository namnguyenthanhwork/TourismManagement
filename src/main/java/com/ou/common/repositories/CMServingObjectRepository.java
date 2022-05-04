package com.ou.common.repositories;


import com.ou.pojos.ServingObjectEntity;

import java.util.List;

public interface CMServingObjectRepository {
    List<Object[]> getServingObjects(Integer pageIndex);
    long getServingObjectAmount();

    ServingObjectEntity getServingObject(String svoSlug);
    ServingObjectEntity getServingObject(Integer svoId);

    boolean createServingObject(ServingObjectEntity servingObjectEntity);

    boolean updateServingObject(ServingObjectEntity servingObjectEntity);

    boolean deleteServingObject(ServingObjectEntity servingObjectEntity);
}
