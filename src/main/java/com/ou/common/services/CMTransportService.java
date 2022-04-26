package com.ou.common.services;

import com.ou.pojos.TransportEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMTransportService {
    JSONArray getTransports(Integer pageIndex);

    TransportEntity getTransportAsObj(String tranSlug);

    JSONObject getTransportAsJsonObj(String tranSlug);

    boolean createTransport(TransportEntity transportEntity);

    boolean updateTransport(TransportEntity transportEntity);

    boolean deleteTransport(TransportEntity transportEntity);
}
