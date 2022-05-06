package com.ou.common.services;

import com.ou.pojos.PostCommentEntity;
import org.json.simple.JSONArray;

import java.util.List;

public interface CMPostCommentService {
    List<PostCommentEntity> getPostCommentByAccount(String accUsername);
    List<PostCommentEntity> getPostCommentByPost(String postSlug);

    JSONArray getPostCommentByTourAsJsonObj(String tourSlug);

    boolean createPostComment(PostCommentEntity postCommentEntity);

    boolean updatePostComment(PostCommentEntity postCommentEntity);

    boolean deletePostComment(PostCommentEntity postCommentEntity);
}
