package com.ou.common.services;

import com.ou.pojos.ServiceEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMServiceService {
    JSONArray getServices(Integer pageIndex);

    long getServiceAmount();
    ServiceEntity getServiceAsObj(String servSlug);

    JSONObject getServiceAsJsonObj(String servSlug);

    boolean createService(ServiceEntity serviceEntity);

    boolean updateService(ServiceEntity serviceEntity);

    boolean deleteService(ServiceEntity serviceEntity);
}
