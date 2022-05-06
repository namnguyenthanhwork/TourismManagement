package com.ou.customer.services;

import com.ou.utils.TourQueryTypeUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CHomePageService {
    JSONArray getTourByCategory(String catSlug);
    JSONArray getTours(TourQueryTypeUtil tourQueryTypeUtil, Integer pageIndex, String params);

    JSONObject  getTour(String tourSlug);
}
