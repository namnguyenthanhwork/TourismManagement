package com.ou.common.services.impl;

import com.ou.common.repositories.CMPostCommentRepository;
import com.ou.common.services.CMPostCommentService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CMPostCommentServiceImpl implements CMPostCommentService {
    @Autowired
    private CMPostCommentRepository cMPostCommentRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public List<PostCommentEntity> getPostCommentByAccount(String accUsername) {
        if (accUsername == null || accUsername.trim().length() == 0)
            return null;
        return cMPostCommentRepository.getPostCommentByAccount(accUsername.trim());
    }

    @Override
    public List<PostCommentEntity> getPostCommentByPost(String postSlug) {
        if (postSlug == null || postSlug.trim().length() == 0)
            return null;
        return cMPostCommentRepository.getPostCommentByPost(postSlug.trim());
    }

    @Override
    public boolean createPostComment(PostCommentEntity postCommentEntity) {
        postCommentEntity.setCmtCreatedDate(utilBeanFactory.getApplicationContext().getBean(Timestamp.class));
        return cMPostCommentRepository.createPostComment(postCommentEntity);
    }

    @Override
    public boolean updatePostComment(PostCommentEntity postCommentEntity) {
        return cMPostCommentRepository.updatePostComment(postCommentEntity);
    }

    @Override
    public boolean deletePostComment(PostCommentEntity postCommentEntity) {
        return cMPostCommentRepository.deletePostComment(postCommentEntity);
    }
}
