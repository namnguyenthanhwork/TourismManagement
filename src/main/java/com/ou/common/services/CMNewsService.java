package com.ou.common.services;

import com.ou.pojos.NewsEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMNewsService {
    JSONArray getNews(Integer pageIndex, String ... params);
    long getNewsAmount(String... params);

    NewsEntity getNewsAsObj(String newsSlug);

    JSONObject getNewsLikeAmount(String newsSlug);

    JSONObject getNewsAsJsonObj(String newsSlug);

    boolean createNews(NewsEntity newsEntity);

    boolean updateNews(NewsEntity newsEntity);

    boolean deleteNews(NewsEntity newsEntity);
}
