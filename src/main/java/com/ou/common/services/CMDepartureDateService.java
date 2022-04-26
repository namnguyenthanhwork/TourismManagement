package com.ou.common.services;

import com.ou.pojos.DepartureDateEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMDepartureDateService {
    JSONArray getDepartureDats(Integer pageIndex);

    DepartureDateEntity getDepartureDatAsObj(Integer dptId);

    JSONObject getDepartureDatAsJsonObj(Integer dptId);

    boolean createDepartureDat(DepartureDateEntity departureDateEntity);

    boolean updateDepartureDat(DepartureDateEntity departureDateEntity);

    boolean deleteDepartureDat(DepartureDateEntity departureDateEntity);
}
