package com.ou.admin.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.PageUtil;
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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/tour-du-lich")
public class ATourController {

    @Autowired
    private CMTourService cMTourService;

    @Autowired
    private CMPostService cMPostService;

    @Autowired
    private CMCategoryService cMCategoryService;

    @Autowired
    private CMAccountService cMAccountService;

    @Autowired
    private CMTourServiceService cMTourServiceService;

    @Autowired
    private CMServiceService cMServiceService;

    @Autowired
    private CMNoteService cMNoteService;

    @Autowired
    private CMServingObjectService cMServingObjectService;

    @Autowired
    private CMTransportService cMTransportService;

    @Autowired
    private CMTourNoteService cMTourNoteService;

    @Autowired
    private CMTourServingObjectService cMTourServingObjectService;

    @Autowired
    private CMTourDepartureDateService cMTourDepartureDateService;

    @Autowired
    private CMTourTransportService cMTourTransportService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    private void setAdditionTourInformation(HttpServletRequest httpServletRequest, Integer tourId) {
        String[] servSlugs = httpServletRequest.getParameterValues("servSlugs");
        String[] noteSlugs = httpServletRequest.getParameterValues("noteSlugs");
        String[] svoSlugs = httpServletRequest.getParameterValues("svoSlugs");
        String[] dptIds = httpServletRequest.getParameterValues("dptIds");
        String[] tranSlugs = httpServletRequest.getParameterValues("tranSlugs");
        if (servSlugs != null && servSlugs.length > 0)
            Arrays.stream(servSlugs).forEach(servSlug -> {
                TourServiceEntity tourService = pojoBeanFactory.getApplicationContext()
                        .getBean(TourServiceEntity.class);
                ServiceEntity service = cMServiceService.getServiceAsObj(servSlug);
                tourService.setTourId(tourId);
                tourService.setServId(service.getServId());
                cMTourServiceService.createTourService(tourService);
            });
        if (noteSlugs != null && noteSlugs.length > 0)
            Arrays.stream(noteSlugs).forEach(noteSlug -> {
                TourNoteEntity tourNote = pojoBeanFactory.getApplicationContext()
                        .getBean(TourNoteEntity.class);
                NoteEntity note = cMNoteService.getNoteAsObj(noteSlug);
                tourNote.setTourId(tourId);
                tourNote.setNoteId(note.getNoteId());
                cMTourNoteService.createTourNote(tourNote);
            });

        if (svoSlugs != null && svoSlugs.length > 0)
            Arrays.stream(svoSlugs).forEach(svoSlug -> {
                TourServingObjectEntity tourServingObject = pojoBeanFactory.getApplicationContext()
                        .getBean(TourServingObjectEntity.class);
                ServingObjectEntity servingObject = cMServingObjectService.getServingObjectAsObj(svoSlug);
                tourServingObject.setTourId(tourId);
                tourServingObject.setSvoId(servingObject.getSvoId());
                tourServingObject.setTourPrice(BigDecimal.valueOf(
                        Long.parseLong(httpServletRequest.getParameter(svoSlug + "Price"))));
                cMTourServingObjectService.createTourServingObject(tourServingObject);
            });

        if (dptIds != null && dptIds.length > 0)
            Arrays.stream(dptIds).forEach(dptId -> {
                TourDepartureDateEntity tourDepartureDate = pojoBeanFactory.getApplicationContext()
                        .getBean(TourDepartureDateEntity.class);
                tourDepartureDate.setTourId(tourId);
                tourDepartureDate.setDptId(Integer.parseInt(dptId));
                tourDepartureDate.setTourAmount(Integer.valueOf(httpServletRequest.getParameter(dptId + "Amount")));
                cMTourDepartureDateService.createTourDepartureDate(tourDepartureDate);
            });

        if (tranSlugs != null && tranSlugs.length > 0)
            Arrays.stream(tranSlugs).forEach(tranSlug -> {
                TourTransportEntity tourTransport = pojoBeanFactory.getApplicationContext()
                        .getBean(TourTransportEntity.class);
                TransportEntity transport = cMTransportService.getTransportAsObj(tranSlug);
                tourTransport.setTourId(tourId);
                tourTransport.setTranId(transport.getTranId());
                cMTourTransportService.createTourTransport(tourTransport);
            });
    }

    // get
    @GetMapping()
    public String getTourView() {
        return "a-tour";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getToursInfo(@RequestParam(required = false) Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        String kw = params.get("kw");
        JSONArray tours = cMTourService.getTours(pageIndex, kw);
        return new ResponseEntity<>(tours, tours.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/thong-tin/tong-quan")
    public ResponseEntity<JSONArray> getToursInfoGeneral() {
        JSONArray tours = cMTourService.getTours();
        return new ResponseEntity<>(tours, tours.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getTourPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMTourService.getTourAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
    // create
    @GetMapping("/tao-moi")
    public String getTourCreatedView() {
        return "a-tour-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTour(HttpServletRequest httpServletRequest,
                             @RequestParam(name = "tourCoverPage", required = false) MultipartFile tourCoverPage) throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        TourEntity tourEntity = pojoBeanFactory.getApplicationContext().getBean(TourEntity.class);
        PostEntity postEntity = pojoBeanFactory.getApplicationContext().getBean(PostEntity.class);
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        CategoryEntity category = cMCategoryService.getCategoryAsObj(httpServletRequest.getParameter("catSlug"));

        postEntity.setAccId(account.getAccId());
        postEntity.setPostTitle(httpServletRequest.getParameter("tourTitle"));
        postEntity.setPostContent(httpServletRequest.getParameter("tourContent"));
        if (tourCoverPage != null && !tourCoverPage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(tourCoverPage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "tour"));
                postEntity.setPostCoverPage((String) url.get("secure_url"));
            } catch (IOException ioException) {
                postEntity.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725598/tour/default_axvqoc.jpg");
            }
        } else
            postEntity.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725598/tour/default_axvqoc.jpg");

        if (cMPostService.createPost(postEntity)) {
            SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
            slugUtil.setSlug(postEntity.getPostTitle());
            PostEntity addedPost = cMPostService.getPostAsObj(slugUtil.getSlug());
            tourEntity.setTourId(addedPost.getPostId());
            tourEntity.setCatId(category.getCatId());
            int saleId = Integer.parseInt(httpServletRequest.getParameter("saleId"));
            if (saleId != -1)
                tourEntity.setSaleId(saleId);

            boolean createdResult = cMTourService.createTour(tourEntity);
            if (!createdResult)
                return "redirect:/quan-tri-vien/tour-du-lich/tao-moi";
            setAdditionTourInformation(httpServletRequest, addedPost.getPostId());
            return "redirect:/quan-tri-vien/tour-du-lich";
        }
        return "redirect:/quan-tri-vien/tour-du-lich/tao-moi";

    }

    // update
    @GetMapping("/{tourSlug}")
    public String getTourEditedView(@PathVariable String tourSlug) {
        return "a-tour-updated";
    }

    @GetMapping("/{tourSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getTourDetail(@PathVariable String tourSlug) {
        JSONObject tour = cMTourService.getTourAsJsonObj(tourSlug);
        if (tour == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tourSlug}", method = RequestMethod.POST)
    public String updateTour(@PathVariable String tourSlug, HttpServletRequest httpServletRequest,
                             @RequestParam(name = "tourCoverPage", required = false) MultipartFile tourCoverPage)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        TourEntity tour = cMTourService.getTourAsObj(tourSlug);
        PostEntity post = cMPostService.getPostAsObj(tourSlug);
        CategoryEntity category = cMCategoryService.getCategoryAsObj(httpServletRequest.getParameter("catSlug"));
        if (tour == null)
            return String.format("redirect:/quan-tri-vien/tour-du-lich/%s", tourSlug);
        post.setPostTitle(httpServletRequest.getParameter("tourTitle"));
        post.setPostContent(httpServletRequest.getParameter("tourContent"));
        if (tourCoverPage != null && !tourCoverPage.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(tourCoverPage.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "tour"));
                post.setPostCoverPage((String) url.get("secure_url"));
            } catch (IOException ioException) {
                if (post.getPostCoverPage() == null || post.getPostCoverPage().length() == 0)
                    post.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725598/tour/default_axvqoc.jpg");
            }
        } else {
            if (post.getPostCoverPage() == null || post.getPostCoverPage().length() == 0)
                post.setPostCoverPage("https://res.cloudinary.com/ou-project/image/upload/v1650725598/tour/default_axvqoc.jpg");
        }
        if (cMPostService.updatePost(post)) {
            tour.setCatId(category.getCatId());
            int saleId = Integer.parseInt(httpServletRequest.getParameter("saleId"));
            if (saleId != -1)
                tour.setSaleId(saleId);
            boolean updatedResult = cMTourService.updateTour(tour);
            if (!updatedResult)
                return String.format("redirect:/quan-tri-vien/tour-du-lich/%s", tourSlug);
            cMTourServiceService.getTourServiceByTour(post.getPostSlug()).forEach(tourServiceEntity ->
                    cMTourServiceService.deleteTourService(tourServiceEntity));
            cMTourNoteService.getTourNoteByTour(post.getPostSlug()).forEach(tourNoteEntity ->
                    cMTourNoteService.deleteTourNote(tourNoteEntity));
            cMTourServingObjectService.getTourServingObjectByTour(post.getPostSlug()).forEach(tourServingObjectEntity ->
                    cMTourServingObjectService.deleteTourServingObject(tourServingObjectEntity));
            cMTourDepartureDateService.getTourDepartureDateByTour(post.getPostSlug()).forEach(tourDepartureDateEntity ->
                    cMTourDepartureDateService.deleteTourDepartureDate(tourDepartureDateEntity));
            cMTourTransportService.getTourTransportByTour(post.getPostSlug()).forEach(tourTransportEntity ->
                    cMTourTransportService.deleteTourTransport(tourTransportEntity));
            setAdditionTourInformation(httpServletRequest, tour.getTourId());
            return "redirect:/quan-tri-vien/tour-du-lich";
        }
        return String.format("redirect:/quan-tri-vien/tour-du-lich/%s", tourSlug);

    }

    // delete
    @RequestMapping(value = "/{tourSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteTour(@PathVariable String tourSlug) {
        PostEntity post = cMPostService.getPostAsObj(tourSlug);
        if (post == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMPostService.deletePost(post);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}

