package com.ou.common.services.impl;

import com.ou.common.repositories.CMPostRepository;
import com.ou.common.services.CMPostService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PostEntity;
import com.ou.utils.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CMPostServiceImpl implements CMPostService {
    @Autowired
    private CMPostRepository cMPostRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public PostEntity getPostAsObj(String postSlug) {
        if (postSlug == null || postSlug.trim().length() == 0)
            return null;
        return cMPostRepository.getPost(postSlug.trim());
    }

    @Override
    public boolean createPost(PostEntity postEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(postEntity.getPostTitle());
        postEntity.setPostSlug(slugUtil.getSlug());
        return cMPostRepository.createPost(postEntity);
    }

    @Override
    public boolean updatePost(PostEntity postEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(postEntity.getPostTitle());
        postEntity.setPostSlug(slugUtil.getSlug());
        return cMPostRepository.updatePost(postEntity);
    }

    @Override
    public boolean deletePost(PostEntity postEntity) {
        return cMPostRepository.deletePost(postEntity);
    }
}
