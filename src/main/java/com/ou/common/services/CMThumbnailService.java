package com.ou.common.services;

import com.ou.pojos.ThumbnailEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface CMThumbnailService {
    JSONArray getThumbnails(Integer pageIndex);

    ThumbnailEntity getThumbnailAsObj(Integer thumId);

    JSONObject getThumbnailAsJsonObj(Integer thumId);

    boolean createThumbnail(ThumbnailEntity thumbnailEntity);

    boolean updateThumbnail(ThumbnailEntity thumbnailEntity);

    boolean deleteThumbnail(ThumbnailEntity thumbnailEntity);
}
