package com.ou.common.services;

import com.ou.pojos.DepartureDateEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMDepartureDateService {
    JSONArray getDepartureDates(Integer pageIndex);

    long getDepartureDateAmount();
    DepartureDateEntity getDepartureDateAsObj(Integer dptId);


    JSONObject getDepartureDateAsJsonObj(Integer dptId);

    boolean createDepartureDate(DepartureDateEntity departureDateEntity);

    boolean updateDepartureDate(DepartureDateEntity departureDateEntity);

    boolean deleteDepartureDate(DepartureDateEntity departureDateEntity);
}
