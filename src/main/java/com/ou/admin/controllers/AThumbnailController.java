package com.ou.admin.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.CMThumbnailService;
import com.ou.common.services.CMTourService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ThumbnailEntity;
import com.ou.pojos.TourEntity;
import com.ou.utils.PageUtil;
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
@RequestMapping(path = "/quan-tri-vien/hinh-thu-nho")
public class AThumbnailController {

    @Autowired
    private CMThumbnailService cMThumbnailService;

    @Autowired
    private CMTourService cMTourService;
    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    // get
    @GetMapping()
    public String getThumbnailsView() {
        return "a-thumbnail";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getThumbnailsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray thumbnails = cMThumbnailService.getThumbnails(pageIndex);
        return new ResponseEntity<>(thumbnails, thumbnails.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getThumbnailPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMThumbnailService.getThumbnailAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    // create
    @GetMapping("/tao-moi")
    public String getThumbnailCreatedView() {
        return "a-thumbnail-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createThumbnail(HttpServletRequest httpServletRequest,
                                                      @RequestParam(name = "thumImage", required = false) MultipartFile thumImage)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ThumbnailEntity thumbnail = pojoBeanFactory.getApplicationContext().getBean(ThumbnailEntity.class);
        TourEntity tour = cMTourService.getTourAsObj(httpServletRequest.getParameter("tourSlug"));
        thumbnail.setTourId(tour.getTourId());
        if (!thumImage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(thumImage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "tourThumbnail"));
                thumbnail.setThumImage((String) url.get("secure_url"));
            } catch (IOException ioException) {
                thumbnail.setThumImage("https://res.cloudinary.com/ou-project/image/upload/v1650725562/tourThumbnail/default_agwzpr.jpg");
            }
        } else
            thumbnail.setThumImage("https://res.cloudinary.com/ou-project/image/upload/v1650725562/tourThumbnail/default_agwzpr.jpg");

        boolean createdResult = cMThumbnailService.createThumbnail(thumbnail);
        if (createdResult)
            return "redirect:/quan-tri-vien/hinh-thu-nho";
        return "redirect:/quan-tri-vien/hinh-thu-nho/tao-moi?error=1";
    }

    // update
    @GetMapping("/{thumId}")
    public String getThumbnailEditedView(@PathVariable Integer thumId) {
        return "a-thumbnail-updated";
    }

    @GetMapping("/{thumId}/chinh-sua")
    public ResponseEntity<JSONObject> getThumbnailDetail(@PathVariable Integer thumId) {
        JSONObject thumbnail = cMThumbnailService.getThumbnailAsJsonObj(thumId);
        if (thumbnail == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(thumbnail, HttpStatus.OK);
    }

    @RequestMapping(value = "/{thumId}", method = RequestMethod.POST)
    public String updateThumbnail(@PathVariable Integer thumId, HttpServletRequest httpServletRequest,
                                                      @RequestParam(name = "thumImage", required = false) MultipartFile thumImage)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ThumbnailEntity thumbnail = cMThumbnailService.getThumbnailAsObj(thumId);
        TourEntity tour = cMTourService.getTourAsObj(httpServletRequest.getParameter("tourSlug"));
        if (thumbnail == null)
            return String.format("redirect:/quan-tri-vien/hinh-thu-nho/%d?error=1", thumId);
        thumbnail.setTourId(tour.getTourId());
        if (thumImage!=null && !thumImage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(thumImage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "tourThumbnail"));
                thumbnail.setThumImage((String) url.get("secure_url"));
            } catch (IOException ioException) {
                if (thumbnail.getThumImage() == null || thumbnail.getThumImage().length() == 0)
                    thumbnail.setThumImage("https://res.cloudinary.com/ou-project/image/upload/v1650725562/tourThumbnail/default_agwzpr.jpg");
            }
        } else {
            if (thumbnail.getThumImage() == null || thumbnail.getThumImage().length() == 0)
                thumbnail.setThumImage("https://res.cloudinary.com/ou-project/image/upload/v1650725562/tourThumbnail/default_agwzpr.jpg");
        }
        boolean updateResult = cMThumbnailService.updateThumbnail(thumbnail);
        if (updateResult)
            return "redirect:/quan-tri-vien/hinh-thu-nho";
        return String.format("redirect:/quan-tri-vien/hinh-thu-nho/%d?error=1", thumId);
    }

    // delete
    @RequestMapping(value = "/{accUthumIdsername}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteThumbnail(@PathVariable Integer thumId) {
        ThumbnailEntity thumbnail = cMThumbnailService.getThumbnailAsObj(thumId);
        if (thumbnail == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMThumbnailService.deleteThumbnail(thumbnail);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
