package com.ou.common.services;

import org.json.simple.JSONArray;

public interface CMBillService {
    JSONArray getBills(Integer pageIndex);
}
