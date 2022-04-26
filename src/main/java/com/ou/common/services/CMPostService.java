package com.ou.common.services;


import com.ou.pojos.PostEntity;

public interface CMPostService {
    PostEntity getPostAsObj(String postSlug);

    boolean createPost(PostEntity postEntity);

    boolean updatePost(PostEntity postEntity);

    boolean deletePost(PostEntity postEntity);
}
