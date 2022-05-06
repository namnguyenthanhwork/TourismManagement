package com.ou.common.services.impl;

import com.ou.common.repositories.CMNewsLikeRepository;
import com.ou.common.services.CMNewsLikeService;
import com.ou.pojos.NewsLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMNewsLikeServiceImpl implements CMNewsLikeService {
    @Autowired
    private CMNewsLikeRepository cMNewsLikeRepository;

    @Override
    public List<NewsLikeEntity> getNewsLikeByAccount(String accUsername) {
        if (accUsername == null || accUsername.trim().length() == 0)
            return null;
        return cMNewsLikeRepository.getNewsLikeByAccount(accUsername.trim());
    }

    @Override
    public List<NewsLikeEntity> getNewsLikeByNews(String newsSlug) {
        if (newsSlug == null || newsSlug.trim().length() == 0)
            return null;
        return cMNewsLikeRepository.getNewsLikeByNews(newsSlug.trim());
    }

    @Override
    public NewsLikeEntity getNewsLike(Integer newsId, Integer accId) {
        if (newsId == null || accId == null)
            return null;
        return cMNewsLikeRepository.getNewsLike(newsId, accId);
    }

    @Override
    public boolean createNewsLike(NewsLikeEntity newsLikeEntity) {
        newsLikeEntity.setLikeStatus(true);
        return cMNewsLikeRepository.createNewsLike(newsLikeEntity);
    }

    @Override
    public boolean updateNewsLike(NewsLikeEntity newsLikeEntity) {
        return cMNewsLikeRepository.updateNewsLike(newsLikeEntity);
    }

    @Override
    public boolean deleteNewsLike(NewsLikeEntity newsLikeEntity) {
        return cMNewsLikeRepository.deleteNewsLike(newsLikeEntity);
    }
}
