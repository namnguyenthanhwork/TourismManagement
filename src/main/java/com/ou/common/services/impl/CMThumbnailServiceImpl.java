package com.ou.common.services.impl;

import com.ou.common.repositories.CMPostRepository;
import com.ou.common.repositories.CMThumbnailRepository;
import com.ou.common.services.CMThumbnailService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.pojos.ThumbnailEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMThumbnailServiceImpl implements CMThumbnailService {

    @Autowired
    private CMThumbnailRepository cMThumbnailRepository;

    @Autowired
    private CMPostRepository cMPostRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getThumbnails(Integer pageIndex) {
        List<Object[]> thumbnails = cMThumbnailRepository.getThumbnails(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        thumbnails.forEach(thumbnail -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("thumId", thumbnail[0]);
            jsonObject.put("thumImage", thumbnail[1]);
            int postId  = (int) thumbnail[2];
            PostEntity post = cMPostRepository.getPost(postId);
            jsonObject.put("tourId", post.getPostId());
            jsonObject.put("tourTitle", post.getPostTitle());
            jsonObject.put("tourSlug", post.getPostSlug());
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getThumbnailAmount() {
        return cMThumbnailRepository.getThumbnailAmount();
    }

    @Override
    public ThumbnailEntity getThumbnailAsObj(Integer thumId) {
        if (thumId == null )
            return null;
        return cMThumbnailRepository.getThumbnail(thumId);
    }

    @Override
    public List<ThumbnailEntity> getThumbnailsByTourId(Integer tourId) {
        if(tourId==null)
            return null;
        return cMThumbnailRepository.getThumbnailsByTourId(tourId);
    }

    @Override
    public JSONArray getThumbnailByTourId(Integer tourId) {
        if(tourId==null)
            return null;
        List<ThumbnailEntity> thumbnailEntities= cMThumbnailRepository.getThumbnailsByTourId(tourId);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        thumbnailEntities.forEach(thumbnailEntity->{
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("thumId", thumbnailEntity.getThumId());
            jsonObject.put("thumImage", thumbnailEntity.getThumImage());
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public JSONObject getThumbnailAsJsonObj(Integer thumId) {
        if (thumId == null)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        ThumbnailEntity thumbnail = cMThumbnailRepository.getThumbnail(thumId);
        if (thumbnail == null)
            return null;
        jsonObject.put("thumId", thumbnail.getThumId());
        jsonObject.put("thumImage", thumbnail.getThumImage());
        PostEntity post = cMPostRepository.getPost(thumbnail.getTourId());
        jsonObject.put("tourId", post.getPostId());
        jsonObject.put("tourTitle", post.getPostTitle());
        jsonObject.put("tourSlug", post.getPostSlug());
        return jsonObject;
    }

    @Override
    public boolean createThumbnail(ThumbnailEntity thumbnailEntity) {
        return cMThumbnailRepository.createThumbnail(thumbnailEntity);
    }

    @Override
    public boolean updateThumbnail(ThumbnailEntity thumbnailEntity) {
        return cMThumbnailRepository.updateThumbnail(thumbnailEntity);
    }

    @Override
    public boolean deleteThumbnail(ThumbnailEntity thumbnailEntity) {
        return cMThumbnailRepository.deleteThumbnail(thumbnailEntity);
    }
}
