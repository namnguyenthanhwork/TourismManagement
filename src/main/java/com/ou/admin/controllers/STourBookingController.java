package com.ou.admin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.common.services.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.MomoUtil;
import com.ou.utils.PageUtil;
import com.ou.utils.SMSUtil;
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
@RequestMapping(path = "/nhan-vien/dat-tour")
public class STourBookingController {

    @Autowired
    private CMPostService cMPostService;

    @Autowired
    private CMTourService cMTourService;

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
    private CMThumbnailService cMThumbnailService;

    @Autowired
    private CMBillTourServingObjectService cMBillTourServingObjectService;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    // get
    @GetMapping()
    public String getTourBookingView() {
        return "s-tour-booking";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getToursInfoInStaff(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        String kw = params.get("kw");
        JSONArray tours = cMTourService.getTours(pageIndex, kw);
        return new ResponseEntity<>(tours, tours.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{tourSlug}")
    public String getTourDetailView(@PathVariable String tourSlug,
                                    @RequestParam(required = false) Map<String, String> params) {
        return "s-tour-booking-detail";
    }

    @GetMapping("/{tourSlug}/thong-tin")
    public ResponseEntity<JSONObject> getTourBookingInfo(@PathVariable String tourSlug) {
        JSONObject tour = cMTourService.getTourAsJsonObj(tourSlug);
        if (tour == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getTourBookingPageAmount() {
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount", pageUtil.getPageAmount(cMTourService.getTourAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    @GetMapping("/{tourId}/hinh-thu-nho")
    public ResponseEntity<JSONArray> getThumbnailsOfTour(@PathVariable Integer tourId) {
        JSONArray jsonArray = cMThumbnailService.getThumbnailByTourId(tourId);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    @GetMapping("/{tourId}/hinh-thuc-thanh-toan")
    public ResponseEntity<JSONArray> getPaymentTypesOfTour(@PathVariable Integer tourId) {
        JSONArray jsonArray = cMPaymentTypeService.getPaymentTypes(null);
        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }

    // booking
    @RequestMapping(value = "/{tourSlug}/thanh-toan", method = RequestMethod.POST)
    public String bookTourStaff(@PathVariable String tourSlug,
                                HttpServletRequest httpServletRequest) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeyException, ExecutionException, JsonProcessingException,
            InterruptedException {
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

        for (int i = 0; i < tsvoIds.size(); i++)
            billTotalMoney = billTotalMoney.add(cMTourServingObjectService.getTourServingObjectById(tsvoIds.get(i))
                    .getTourPrice().multiply(new BigDecimal(tourAmounts.get(i))));

        if (tour.getSaleId() != null) {
            saleEntity = cMSaleService.getSaleAsObj(tour.getSaleId());
            SalePercentEntity salePercent = cMSalePercentService.getSalePercentAsObj(saleEntity.getSperId());
            long currentTime = System.currentTimeMillis();
            if (saleEntity.getSaleFromDate().getTime() <= currentTime && currentTime <= saleEntity.getSaleToDate().getTime())
                billTotalSaleMoney = billTotalMoney.divide(new BigDecimal(100), RoundingMode.HALF_EVEN)
                        .multiply(new BigDecimal(salePercent.getSperPercent()));
        }
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDateAsObj(
                Integer.valueOf(httpServletRequest.getParameter("dptId")));
        bill.setBillDepartureDate(departureDate.getDptDate());
        bill.setAccId(account.getAccId());
        bill.setPaytId(paymentType.getPaytId());
        bill.setBillTotalMoney(billTotalMoney);
        bill.setBillTotalSaleMoney(billTotalSaleMoney);
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
        if ("thanh-toan-momo".equals(paymentType.getPaytSlug())) {
            String url = String.format("http://localhost:8080/TourismManagement/nhan-vien/dat-tour/%s", tourSlug);
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
        return "redirect:/nhan-vien/dat-tour";
    }

    @PostMapping("/thanh-toan/cap-nhat")
    public ResponseEntity<HttpStatus> updateBillStaff(@RequestBody Map<String, String> body) {
        Integer billId = Integer.valueOf(body.get("billId"));
        BillEntity bill = cMBillService.getBillAsObj(billId);
        bill.setBillIsPaid(true);
        boolean result = cMBillService.updateBill(bill);
        return new ResponseEntity<>(result ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
