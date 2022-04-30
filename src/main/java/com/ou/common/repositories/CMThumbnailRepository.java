package com.ou.common.repositories;


import com.ou.pojos.ThumbnailEntity;

import java.util.List;

public interface CMThumbnailRepository {
    List<Object[]> getThumbnails(Integer pageIndex);

    ThumbnailEntity getThumbnail(Integer thumId);

    List<ThumbnailEntity> getThumbnailsByTourId(Integer tourId);

    boolean createThumbnail(ThumbnailEntity thumbnailEntity);

    boolean updateThumbnail(ThumbnailEntity thumbnailEntity);

    boolean deleteThumbnail(ThumbnailEntity thumbnailEntity);
}
