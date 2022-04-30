package com.ou.admin.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.CMAccountService;
import com.ou.common.services.CMNewsService;
import com.ou.common.services.CMPostService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.NewsEntity;
import com.ou.pojos.PostEntity;
import com.ou.utils.SlugUtil;
import com.ou.utils.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/tin-tuc")
public class ANewsController {
//
    @Autowired
    private CMNewsService cMNewsService;

    @Autowired
    private CMPostService cMPostService;

    @Autowired
    private CMAccountService cMAccountService;
    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getNewsView() {
        return "a-news";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getNewsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        String kw= params.get("kw");
        JSONArray news = cMNewsService.getNews(pageIndex, kw);
        return new ResponseEntity<>(news, news.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getNewsCreatedView() {
        return "a-news-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createNews(HttpServletRequest httpServletRequest,
            @RequestParam(name = "newsCoverPage", required = false) MultipartFile newsCoverPage)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        NewsEntity newsEntity = pojoBeanFactory.getApplicationContext().getBean(NewsEntity.class);
        PostEntity postEntity = pojoBeanFactory.getApplicationContext().getBean(PostEntity.class);
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        postEntity.setAccId(account.getAccId());
        postEntity.setPostTitle(httpServletRequest.getParameter("newsName"));
        postEntity.setPostContent(httpServletRequest.getParameter("newsContent"));
        if (!newsCoverPage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(newsCoverPage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "news"));
                postEntity.setPostCoverPage((String) url.get("secure_url"));
            } catch (IOException ioException) {

                    postEntity.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725574/news/default_sh0tqg.jpg");
            }
        } else

                postEntity.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725574/news/default_sh0tqg.jpg");

        if (cMPostService.createPost(postEntity)) {
            SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
            slugUtil.setSlug(postEntity.getPostTitle());
            PostEntity addedPost = cMPostService.getPostAsObj(slugUtil.getSlug());
            newsEntity.setNewsId(addedPost.getPostId());
            boolean createdResult = cMNewsService.createNews(newsEntity);
            return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    // update
    @GetMapping("/{newsSlug}")
    public String getNewsEditedView(@PathVariable String newsSlug) {
        return "a-news-updated";
    }

    @GetMapping("/{newsSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getNewsDetail(@PathVariable String newsSlug) {
        JSONObject news = cMNewsService.getNewsAsJsonObj(newsSlug);
        if (news == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @RequestMapping(value = "/{newsSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateNews(@PathVariable String newsSlug, HttpServletRequest httpServletRequest,
         @RequestParam(name = "newsCoverPage", required = false) MultipartFile newsCoverPage) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        NewsEntity news = cMNewsService.getNewsAsObj(newsSlug);
        PostEntity post = cMPostService.getPostAsObj(newsSlug);
        if (news == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        post.setPostTitle(httpServletRequest.getParameter("newsName"));
        post.setPostContent(httpServletRequest.getParameter("newsContent"));
        if (!newsCoverPage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(newsCoverPage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "news"));
                post.setPostCoverPage((String) url.get("secure_url"));
            } catch (IOException ioException) {
                if (post.getPostCoverPage() == null || post.getPostCoverPage().length() == 0)
                    post.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725574/news/default_sh0tqg.jpg");
            }
        } else {
            if (post.getPostCoverPage() == null || post.getPostCoverPage().length() == 0)
                post.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725574/news/default_sh0tqg.jpg");
        }
        if (cMPostService.updatePost(post)) {
            boolean updatedResult = cMNewsService.updateNews(news);
            return new ResponseEntity<>(updatedResult ? HttpStatus.OK : HttpStatus.CONFLICT);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{newsSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable String newsSlug) {
        NewsEntity news = cMNewsService.getNewsAsObj(newsSlug);
        PostEntity post = cMPostService.getPostAsObj(newsSlug);
        if (news == null || post ==null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        if(cMPostService.deletePost(post)){
            boolean deleteResult = cMNewsService.deleteNews(news);
            return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
