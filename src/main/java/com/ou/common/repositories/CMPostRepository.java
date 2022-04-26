package com.ou.common.repositories;


import com.ou.pojos.PostEntity;

public interface CMPostRepository {
    PostEntity getPost(String postSlug);
    PostEntity getPost(Integer postId);

    boolean createPost(PostEntity postEntity);

    boolean updatePost(PostEntity postEntity);

    boolean deletePost(PostEntity postEntity);
}
