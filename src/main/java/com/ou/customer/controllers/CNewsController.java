package com.ou.customer.controllers;

import com.ou.common.services.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.PageUtil;
import com.ou.utils.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/tin-tuc")
public class CNewsController {

    @Autowired
    private CMNewsService cMNewsService;
    @Autowired
    private CMPostCommentService cMPostCommentService;
    @Autowired
    private CMPostService cMPostService;
    @Autowired
    private CMAccountService cMAccountService;

    @Autowired
    private CMNewsLikeService cMNewsLikeService;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @GetMapping()
    public String getCustomerNewsView() {
        return "c-news";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getNewsInfoCustomer(@RequestParam(required = false) Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray news = cMNewsService.getNews(pageIndex);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getNewsPageAmountCustomer() {
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount", pageUtil.getPageAmount(cMNewsService.getNewsAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/{newsSlug}")
    public String getCustomerNewsDetailView(@PathVariable String newsSlug) {
        return "c-news-detail";
    }


    @GetMapping("/{newsSlug}/thong-tin")
    public ResponseEntity<JSONObject> getCustomerNewsDetailData(@PathVariable String newsSlug) {
        JSONObject news = cMNewsService.getNewsAsJsonObj(newsSlug);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    //comment
    @GetMapping("/{newsSlug}/binh-luan")
    public ResponseEntity<JSONArray> getCommentsNews(@PathVariable String newsSlug) {
        JSONArray jsonArray = cMPostCommentService.getPostCommentByTourAsJsonObj(newsSlug);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @PostMapping("/{newsSlug}/binh-luan")
    public ResponseEntity<HttpStatus> createCommentNews(@PathVariable String newsSlug,
                                                        @RequestBody Map<String, String> body) {
        String cmtContent = body.get("cmtContent");
        PostCommentEntity postComment = pojoBeanFactory.getApplicationContext().getBean(PostCommentEntity.class);
        postComment.setCmtContent(cmtContent);
        postComment.setAccId(cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername()).getAccId());
        postComment.setPostId(cMPostService.getPostAsObj(newsSlug).getPostId());
        boolean result = cMPostCommentService.createPostComment(postComment);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // like
    @GetMapping("/{newsSlug}/luot-thich")
    public ResponseEntity<JSONObject> getNewsLikeAmount(@PathVariable String newsSlug) {
        JSONObject jsonObject = cMNewsService.getNewsLikeAmount(newsSlug);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/{newsSlug}/trang-thai-thich")
    public ResponseEntity<Boolean> getNewsLikeStatus(@PathVariable String newsSlug) {
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        PostEntity post = cMPostService.getPostAsObj(newsSlug);
        NewsLikeEntity newsLike = cMNewsLikeService.getNewsLike(post.getPostId(), account.getAccId());
        boolean status = newsLike != null && newsLike.getLikeStatus();
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @PostMapping("/{newsSlug}/luot-thich")
    public ResponseEntity<HttpStatus> likeNews(@PathVariable String newsSlug, @RequestBody Map<String, String> body) {
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        PostEntity post = cMPostService.getPostAsObj(newsSlug);
        NewsEntity news = cMNewsService.getNewsAsObj(newsSlug);
        NewsLikeEntity newsLike = cMNewsLikeService.getNewsLike(post.getPostId(), account.getAccId());
        if (newsLike == null) {
            news.setNewsLikeAmount(news.getNewsLikeAmount() + 1);
            newsLike = pojoBeanFactory.getApplicationContext().getBean(NewsLikeEntity.class);
            newsLike.setNewsId(post.getPostId());
            newsLike.setAccId(account.getAccId());
            boolean result = cMNewsService.updateNews(news);
            if (result) {
                result = cMNewsLikeService.createNewsLike(newsLike);
                return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        newsLike.setLikeStatus(!newsLike.getLikeStatus());
        if (newsLike.getLikeStatus())
            news.setNewsLikeAmount(news.getNewsLikeAmount() + 1);
        else
            news.setNewsLikeAmount(news.getNewsLikeAmount() - 1);
        boolean result = cMNewsService.updateNews(news);
        if (result) {
            result = cMNewsLikeService.updateNewsLike(newsLike);
            return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
