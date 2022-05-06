package com.ou.common.services.impl;

import com.ou.common.repositories.CMAccountRepository;
import com.ou.common.repositories.CMPostCommentRepository;
import com.ou.common.services.CMPostCommentService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.PostCommentEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CMPostCommentServiceImpl implements CMPostCommentService {
    @Autowired
    private CMPostCommentRepository cMPostCommentRepository;

    @Autowired
    private CMAccountRepository cMAccountRepository;

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
    public JSONArray getPostCommentByTourAsJsonObj(String tourSlug) {
        if (tourSlug == null || tourSlug.trim().length() == 0)
            return null;
        List<PostCommentEntity> postComments = cMPostCommentRepository.getPostCommentByPost(tourSlug);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        postComments.forEach(postCommentEntity -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            AccountEntity account = cMAccountRepository.getAccount(postCommentEntity.getAccId());
            jsonObject.put("accId", account.getAccId());
            jsonObject.put("accName", String.format("%s %s", account.getAccLastName(), account.getAccFirstName()));
            jsonObject.put("accAvatar", account.getAccAvatar());
            jsonObject.put("commentId", postCommentEntity.getCmtId());
            jsonObject.put("commentContent", postCommentEntity.getCmtContent());
            jsonObject.put("postCreatedDate", postCommentEntity.getCmtCreatedDate());
            jsonArray.add(jsonObject);
        });
        return jsonArray;

    }

    @Override
    public boolean createPostComment(PostCommentEntity postCommentEntity) {
        postCommentEntity.setCmtCreatedDate(utilBeanFactory.getApplicationContext().getBean("currentTimeStamp",Timestamp.class));
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
