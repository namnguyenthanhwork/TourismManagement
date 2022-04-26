package com.ou.common.repositories;



import com.ou.pojos.NewsLikeEntity;

import java.util.List;

public interface CMNewsLikeRepository {
    List<NewsLikeEntity> getNewsLikeByAccount(String accUsername);
    List<NewsLikeEntity> getNewsLikeByNews(String newsSlug);

    boolean createNewsLike(NewsLikeEntity newsLikeEntity);

    boolean updateNewsLike(NewsLikeEntity newsLikeEntity);

    boolean deleteNewsLike(NewsLikeEntity newsLikeEntity);
}
