package com.ou.common.services.impl;

import com.ou.common.repositories.CMNewsRepository;
import com.ou.common.repositories.CMPostRepository;
import com.ou.common.services.CMNewsService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.NewsEntity;
import com.ou.pojos.PostEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CMNewsServiceImpl implements CMNewsService {
    @Autowired
    private CMNewsRepository cMNewsRepository;

    @Autowired
    private CMPostRepository cMPostRepository;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getNews(Integer pageIndex, String... params) {
        List<Object[]> newsList = cMNewsRepository.getNewsList(pageIndex, params);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        newsList.forEach(news -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("newsId", news[0]);
            jsonObject.put("newsCreatedDate", news[1]);
            jsonObject.put("newsLikeAmount", news[2]);
            jsonObject.put("newsTitle", news[3]);
            jsonObject.put("newsSlug", news[4]);
            jsonObject.put("newsCoverPage", news[5]);
            jsonObject.put("newsDescription", news[6]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }

    @Override
    public long getNewsAmount(String... params) {
        return cMNewsRepository.getNewsAmount(params);
    }

    @Override
    public NewsEntity getNewsAsObj(String newsSlug) {
        if (newsSlug == null || newsSlug.trim().length() == 0)
            return null;
        return cMNewsRepository.getNews(newsSlug.trim());
    }

    @Override
    public JSONObject getNewsLikeAmount(String newsSlug) {
        if (newsSlug == null || newsSlug.trim().length() == 0)
            return null;
        NewsEntity news = cMNewsRepository.getNews(newsSlug);
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        jsonObject.put("newsLikeAmount", news.getNewsLikeAmount());
        return jsonObject;
    }

    @Override
    public JSONObject getNewsAsJsonObj(String newsSlug) {
        if (newsSlug == null || newsSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        NewsEntity news = cMNewsRepository.getNews(newsSlug.trim());
        if (news == null)
            return null;
        jsonObject.put("newsId", news.getNewsId());
        jsonObject.put("newsCreatedDate", news.getNewsCreatedDate());
        jsonObject.put("newsLikeAmount", news.getNewsLikeAmount());
        jsonObject.put("newsDescription", news.getNewsDescription());
        PostEntity post = cMPostRepository.getPost(news.getNewsId());
        jsonObject.put("newsTitle", post.getPostTitle());
        jsonObject.put("newsSlug", post.getPostSlug());
        jsonObject.put("newsContent", post.getPostContent());
        jsonObject.put("newsCoverPage", post.getPostCoverPage());
        return jsonObject;
    }

    @Override
    public boolean createNews(NewsEntity newsEntity) {
        newsEntity.setNewsLikeAmount(0);
        newsEntity.setNewsCreatedDate(utilBeanFactory.getApplicationContext().getBean("currentTimeStamp", Timestamp.class));
        return cMNewsRepository.createNews(newsEntity);
    }

    @Override
    public boolean updateNews(NewsEntity newsEntity) {
        return cMNewsRepository.updateNews(newsEntity);
    }

    @Override
    public boolean deleteNews(NewsEntity newsEntity) {
        return cMNewsRepository.deleteNews(newsEntity);
    }
}
