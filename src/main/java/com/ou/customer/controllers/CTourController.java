package com.ou.customer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.common.services.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.customer.services.CHomePageService;
import com.ou.pojos.*;
import com.ou.utils.MomoUtil;
import com.ou.utils.PageUtil;
import com.ou.utils.TourQueryTypeUtil;
import com.ou.utils.UserUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/tour-du-lich")
public class CTourController {

    @Autowired
    private CMTourService cMTourService;

    @Autowired
    private CMThumbnailService cMThumbnailService;

    @Autowired
    private CMPostService cMPostService;

    @Autowired
    private CMAccountService cMAccountService;

    @Autowired
    private CMServingObjectService cMServingObjectService;

    @Autowired
    private CMTourServingObjectService cMTourServingObjectService;

    @Autowired
    private CMSaleService cMSaleService;

    @Autowired
    private CMSalePercentService cMSalePercentService;

    @Autowired
    private CMPaymentTypeService cMPaymentTypeService;

    @Autowired
    private CMDepartureDateService cMDepartureDateService;

    @Autowired
    private CMBillService cMBillService;

    @Autowired
    private CMBillTourServingObjectService cMBillTourServingObjectService;

    @Autowired
    private CMTourRatingService cMTourRatingService;

    @Autowired
    private CMPostCommentService cMPostCommentService;

    @Autowired
    private CMTourDepartureDateService cMTourDepartureDateService;

    @Autowired
    private CHomePageService cHomePageService;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;


    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getToursInfoCustomer(@RequestParam(required = false) Map<String, String> params) {
        String type = params.get("loai");
        TourQueryTypeUtil tourQueryTypeUtil = null;
        if (type != null)
            switch (type) {
                case "gia" -> tourQueryTypeUtil = TourQueryTypeUtil.PRICE;
                case "lich-trinh" -> tourQueryTypeUtil = TourQueryTypeUtil.SCHEDULE;
                case "ten" -> tourQueryTypeUtil = TourQueryTypeUtil.KEYWORD;
            }
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        String kw = params.get("kw");
        JSONArray tours = cHomePageService.getTours(tourQueryTypeUtil, pageIndex, kw);
        return new ResponseEntity<>(tours, HttpStatus.OK);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getTourPageAmountCustomer(@RequestParam(required = false) Map<String, String> params) {
        String type = params.get("loai");
        TourQueryTypeUtil tourQueryTypeUtil = null;
        if (type != null)
            switch (type) {
                case "gia" -> tourQueryTypeUtil = TourQueryTypeUtil.PRICE;
                case "lich-trinh" -> tourQueryTypeUtil = TourQueryTypeUtil.SCHEDULE;
                case "ten" -> tourQueryTypeUtil = TourQueryTypeUtil.KEYWORD;
            }
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount", pageUtil.getPageAmount(cHomePageService.getTourAmount(tourQueryTypeUtil,params.get("kw"))));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/{tourSlug}")
    public String getCustomerTourDetailView() {
        return "c-tour-detail";
    }

    @GetMapping("/{tourSlug}/thong-tin")
    public ResponseEntity<JSONObject> getCustomerTourDetailData(@PathVariable String tourSlug) {
        JSONObject tour = cMTourService.getTourAsJsonObj(tourSlug);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @GetMapping("/{tourSlug}/thong-tin-tong-quan")
    public ResponseEntity<JSONObject> getCustomerGeneralTourDetailData(@PathVariable String tourSlug) {
        JSONObject tour = cHomePageService.getTour(tourSlug);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @GetMapping("/{tourSlug}/hinh-thu-nho")
    public ResponseEntity<JSONArray> getThumbnailsOfTour(@PathVariable String tourSlug) {
        TourEntity tour = cMTourService.getTourAsObj(tourSlug);
        JSONArray jsonArray = cMThumbnailService.getThumbnailByTourId(tour.getTourId());
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }


    @GetMapping("/{tourSlug}/thanh-toan")
    public String getTourBookingDetailView(@PathVariable String tourSlug) {
        return "c-tour-booking-detail";
    }

    @GetMapping("/{tourSlug}/thanh-toan/thong-tin")
    public ResponseEntity<JSONObject> getTourBookingInfoCustomer(@PathVariable String tourSlug) {
        JSONObject tour = cMTourService.getTourAsJsonObj(tourSlug);
        if (tour == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @GetMapping("/hinh-thuc-thanh-toan")
    public ResponseEntity<JSONArray> getPaymentTypesOfTourCustomer() {
        JSONArray jsonArray = cMPaymentTypeService.getPaymentTypes(null);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    // booking
    @RequestMapping(value = "/{tourSlug}/thanh-toan", method = RequestMethod.POST)
    public String bookTourCustomer(@PathVariable String tourSlug,
                                   HttpServletRequest httpServletRequest) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeyException, ExecutionException, JsonProcessingException,
            InterruptedException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");

        List<String> svoSlugs = utilBeanFactory.getApplicationContext().getBean("stringList", List.class);
        cMServingObjectService.getServingObjects(null).forEach(svo ->
                svoSlugs.add((String) ((JSONObject) svo).get("svoSlug")));
        List<Integer> tourAmounts = utilBeanFactory.getApplicationContext().getBean("numberList", List.class);
        List<Integer> tsvoIds = utilBeanFactory.getApplicationContext().getBean("numberList", List.class);
        svoSlugs.forEach(svoSlug -> {
            String param = httpServletRequest.getParameter(svoSlug);
            if (param == null || Integer.parseInt(param) == 0)
                return;
            List<TourServingObjectEntity> tourServingObjects = cMTourServingObjectService
                    .getTourServingObjectByTour(tourSlug);
            tourServingObjects.forEach(tsvo -> {
                if (cMServingObjectService.getServingObject(tsvo.getSvoId()).getSvoSlug().equals(svoSlug))
                    tsvoIds.add(tsvo.getTsvoId());
            });
            tourAmounts.add(Integer.valueOf(param));
        });

        TourEntity tour = cMTourService.getTourAsObj(tourSlug);
        PostEntity post = cMPostService.getPostAsObj(tourSlug);
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        PaymentTypeEntity paymentType = cMPaymentTypeService.getPaymentTypeAsObj(
                httpServletRequest.getParameter("paytSlug"));
        BillEntity bill = pojoBeanFactory.getApplicationContext().getBean(BillEntity.class);
        BigDecimal billTotalMoney = utilBeanFactory.getApplicationContext().getBean(BigDecimal.class);
        BigDecimal billTotalSaleMoney = utilBeanFactory.getApplicationContext().getBean(BigDecimal.class);
        SaleEntity saleEntity;
        int amount = 0;
        for (int i = 0; i < tsvoIds.size(); i++) {
            amount += tourAmounts.get(i);
            billTotalMoney = billTotalMoney.add(cMTourServingObjectService.getTourServingObjectById(tsvoIds.get(i))
                    .getTourPrice().multiply(new BigDecimal(amount)));
        }

        //empty slot
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDateAsObj(
                Integer.valueOf(httpServletRequest.getParameter("dptId")));
        TourDepartureDateEntity tourDepartureDate =
                cMTourDepartureDateService.getTourDepartureDateEntity(tour.getTourId(), departureDate.getDptId());
        if(tourDepartureDate==null || tourDepartureDate.getTourAmount()-tourDepartureDate.getTourSellAmount() < amount)
            return String.format("redirect:/tour-du-lich/%s/thanh-toan?error=1", tourSlug);
        tourDepartureDate.setTourSellAmount(tourDepartureDate.getTourSellAmount()+amount);
        cMTourDepartureDateService.updateTourDepartureDate(tourDepartureDate);

        //sale of bill
        if (tour.getSaleId() != null) {
            saleEntity = cMSaleService.getSaleAsObj(tour.getSaleId());
            SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(saleEntity.getSperId());
            long currentTime = System.currentTimeMillis();
            if (saleEntity.getSaleFromDate().getTime() <= currentTime && currentTime <= saleEntity.getSaleToDate().getTime())
                billTotalSaleMoney = billTotalMoney.divide(new BigDecimal(100), RoundingMode.HALF_EVEN)
                        .multiply(new BigDecimal(salePercent.getSperPercent()));
        }

        // set bill
        bill.setBillDepartureDate(departureDate.getDptDate());
        bill.setAccId(account.getAccId());
        bill.setPaytId(paymentType.getPaytId());
        bill.setBillTotalMoney(billTotalMoney);
        bill.setBillTotalSaleMoney(billTotalSaleMoney);
        if(httpServletRequest.getParameter("billShipDate")!=null){
            Timestamp billShipDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
            billShipDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(
                    httpServletRequest.getParameter("billShipDate") + " 00:00:00").getTime());
            bill.setBillShipDate(billShipDate);
            bill.setBillShipCity(httpServletRequest.getParameter("billShipCity"));
            bill.setBillShipDistrict(httpServletRequest.getParameter("billShipDistrict"));
            bill.setBillShipAddress(httpServletRequest.getParameter("billShipAddress"));
        }
        BillEntity createdBill = cMBillService.createBill(bill);
        AtomicReference<Integer> pivot =
                utilBeanFactory.getApplicationContext().getBean("atomicReference", AtomicReference.class);
        tsvoIds.forEach(tsvoId -> {
            BillTourServingObjectEntity billTourServingObjectEntity = pojoBeanFactory.getApplicationContext()
                    .getBean(BillTourServingObjectEntity.class);
            billTourServingObjectEntity.setTourAmount(tourAmounts.get(pivot.get()));
            billTourServingObjectEntity.setBillId(createdBill.getBillId());
            billTourServingObjectEntity.setTsvoId(tsvoId);
            cMBillTourServingObjectService.createBillTourServingObject(billTourServingObjectEntity);
            pivot.set(pivot.get() + 1);
        });

        BigDecimal price = billTotalMoney.subtract(billTotalSaleMoney);
        String orderInfo = String.format("Mã tour: %d - Tên tour: %s - " +
                        "Mã nhân viên đăng kí: %d - Tên nhân viên đăng kí: %s",
                post.getPostId(),
                post.getPostTitle(),
                account.getAccId(),
                account.getAccLastName() + " " + account.getAccFirstName());
        if(bill.getBillShipDate()!=null)
            orderInfo+= String.format(" - Ngày giao hàng: %s - Địa chỉ: %s, %s, %s",
                    String.format("%1$TD %1$TT", bill.getBillShipDate()),
                    bill.getBillShipAddress(),
                    bill.getBillShipDistrict(),
                    bill.getBillShipCity());
        if ("thanh-toan-momo".equals(paymentType.getPaytSlug())) {
            String url = String.format("http://localhost:8080/TourismManagement/tour-du-lich/%s/thanh-toan", tourSlug);
            return String.format("redirect:%s&%d",
                    utilBeanFactory.getApplicationContext().getBean(MomoUtil.class)
                            .createOrder(price, orderInfo, url, url).get("payUrl"),
                    createdBill.getBillId());
        }
        createdBill.setBillIsPaid(true);
        orderInfo += String.format(" - Giá: %s", price);
        cMBillService.updateBill(createdBill);
//        utilBeanFactory.getApplicationContext().getBean(SMSUtil.class)
//                .sendMessage(httpServletRequest.getParameter("phoneNumber"), orderInfo);
        return String.format("redirect:/tour-du-lich/%s", tourSlug);
    }

    //comment
    @GetMapping("/{tourSlug}/binh-luan")
    public ResponseEntity<JSONArray> getCommentsTour(@PathVariable String tourSlug) {
        JSONArray jsonArray = cMPostCommentService.getPostCommentByTourAsJsonObj(tourSlug);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @PostMapping("/{tourSlug}/binh-luan")
    public ResponseEntity<HttpStatus> createCommentTour(@PathVariable String tourSlug, @RequestBody Map<String, String> body) {
        String cmtContent = body.get("cmtContent");
        PostCommentEntity postComment = pojoBeanFactory.getApplicationContext().getBean(PostCommentEntity.class);
        postComment.setCmtContent(cmtContent);
        postComment.setAccId(cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername()).getAccId());
        postComment.setPostId(cMPostService.getPostAsObj(tourSlug).getPostId());
        boolean result = cMPostCommentService.createPostComment(postComment);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    //rating
    @GetMapping("/{tourSlug}/ti-le-danh-gia")
    public ResponseEntity<JSONObject> getRatingDetailOfTour(@PathVariable String tourSlug) {
        TourEntity tour = cMTourService.getTourAsObj(tourSlug);
        JSONObject jsonObject = cMTourRatingService.getTourRatingAmount(tour.getTourId());
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/{tourSlug}/ti-le-danh-gia/so-luong")
    public ResponseEntity<JSONObject> getRatingAmount(@PathVariable String tourSlug){
        JSONObject jsonObject = cMTourService.getTourAverageRating(tourSlug);
        return  new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @PostMapping("/{tourSlug}/ti-le-danh-gia")
    public ResponseEntity<HttpStatus> rateTour(@PathVariable String tourSlug, @RequestBody Map<String, String> body) {
        AccountEntity account = cMAccountService.getAccountAsObj(UserUtil.getCurrentUsername());
        PostEntity post = cMPostService.getPostAsObj(tourSlug);
        TourEntity tour = cMTourService.getTourAsObj(tourSlug);
        int curTotalRating = cMTourRatingService.getTotalTourRatingAmount(post.getPostId());
        int curRatingAmount =cMTourRatingService.getTourRatingRecordAmount(post.getPostId());
        TourRatingEntity tourRating = cMTourRatingService.getTourRating(post.getPostId(), account.getAccId());
        int rateAmount =Integer.parseInt(body.get("rateAmount"));
        if (tourRating == null) {
            tour.setTourAverageRating((int) Math.floor((curTotalRating*1.0+rateAmount)/(curRatingAmount+1)));
            tourRating = pojoBeanFactory.getApplicationContext().getBean(TourRatingEntity.class);
            tourRating.setTourId(post.getPostId());
            tourRating.setRateAmount(rateAmount);
            tourRating.setAccId(account.getAccId());
            boolean result = cMTourService.updateTour(tour);
            if(result) {
                result = cMTourRatingService.createTourRating(tourRating);
                return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>( HttpStatus.CONFLICT);
        }
        tour.setTourAverageRating((int) Math.floor((curTotalRating-tourRating.getRateAmount()+rateAmount)*1.0/curRatingAmount));
        tourRating.setRateAmount(rateAmount);
        boolean result = cMTourService.updateTour(tour);
        if(result) {
            result = cMTourRatingService.updateTourRating(tourRating) ;
            return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>( HttpStatus.CONFLICT);
    }

}
