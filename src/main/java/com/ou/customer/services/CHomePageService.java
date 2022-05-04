package com.ou.customer.services;

import org.json.simple.JSONArray;

public interface CHomePageService {
    JSONArray getTourByCategory(String catSlug);
}
