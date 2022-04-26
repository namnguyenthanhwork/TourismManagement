package com.ou.common.repositories;

import com.ou.pojos.PostCommentEntity;

import java.util.List;

public interface CMPostCommentRepository {
    List<PostCommentEntity> getPostCommentByAccount(String accUsername);
    List<PostCommentEntity> getPostCommentByPost(String postSlug);

    boolean createPostComment(PostCommentEntity postCommentEntity);

    boolean updatePostComment(PostCommentEntity postCommentEntity);

    boolean deletePostComment(PostCommentEntity postCommentEntity);
}
