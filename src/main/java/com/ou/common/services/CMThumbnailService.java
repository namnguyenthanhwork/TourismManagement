package com.ou.common.services;

import com.ou.pojos.ThumbnailEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface CMThumbnailService {
    JSONArray getThumbnails(Integer pageIndex);
    long getThumbnailAmount();
    ThumbnailEntity getThumbnailAsObj(Integer thumId);

    List<ThumbnailEntity> getThumbnailsByTourId(Integer tourId);

    JSONArray getThumbnailByTourId(Integer tourId);

    JSONObject getThumbnailAsJsonObj(Integer thumId);

    boolean createThumbnail(ThumbnailEntity thumbnailEntity);

    boolean updateThumbnail(ThumbnailEntity thumbnailEntity);

    boolean deleteThumbnail(ThumbnailEntity thumbnailEntity);
}
