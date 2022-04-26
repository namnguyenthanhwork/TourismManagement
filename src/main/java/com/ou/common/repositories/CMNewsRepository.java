package com.ou.common.repositories;


import com.ou.pojos.NewsEntity;

import java.util.List;

public interface CMNewsRepository {
    List<Object[]> getNewsList(Integer pageIndex, String... params);

    NewsEntity getNews(String newsSlug);

    NewsEntity getNews(Integer newsId);

    boolean createNews(NewsEntity newsEntity);

    boolean updateNews(NewsEntity newsEntity);

    boolean deleteNews(NewsEntity newsEntity);
}
