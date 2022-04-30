package com.ou.admin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ou.common.services.*;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.*;
import com.ou.utils.MomoUtil;
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
    private CMBillService cMBillService;

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
    public String getTourDetailView(@PathVariable String tourSlug) {
        return "a-tour-detail";
    }

    @GetMapping("/{tourSlug}/thong-tin")
    public ResponseEntity<JSONObject> getTourBookingInfo(@PathVariable String tourSlug) {
        JSONObject tour = cMTourService.getTourAsJsonObj(tourSlug);
        if (tour == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tour, HttpStatus.OK);
    }

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
            List<TourServingObjectEntity> tourServingObjects = cMTourServingObjectService
                    .getTourServingObjectByTour(tourSlug);
            tourServingObjects.forEach(tsvo -> {
                if (cMServingObjectService.getServingObject(tsvo.getSvoId()).getSvoSlug().equals(svoSlug))
                    tsvoIds.add(tsvo.getTsvoId());
            });
            tourAmounts.add(Integer.valueOf(httpServletRequest.getParameter(svoSlug)));
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
            billTotalSaleMoney = billTotalMoney.divide(new BigDecimal(100), RoundingMode.HALF_EVEN)
                    .multiply(new BigDecimal(salePercent.getSperPercent()));
        }
        bill.setAccId(account.getAccId());
        bill.setPaytId(paymentType.getPaytId());
        bill.setBillTotalMoney(billTotalMoney);
        bill.setBillTotalSaleMoney(billTotalSaleMoney);
        BillEntity createdBill = cMBillService.createBill(bill);
        List<BillTourServingObjectEntity> billTourServingObjectEntities =
                pojoBeanFactory.getApplicationContext().getBean("billTourServingObjectEntities", List.class);
        AtomicReference<Integer> pivot =
                utilBeanFactory.getApplicationContext().getBean("atomicReference", AtomicReference.class);
        tsvoIds.forEach(tsvoId -> {
            BillTourServingObjectEntity billTourServingObjectEntity = pojoBeanFactory.getApplicationContext()
                    .getBean(BillTourServingObjectEntity.class);
            billTourServingObjectEntity.setTourAmount(tourAmounts.get(pivot.get()));
            billTourServingObjectEntity.setBillId(createdBill.getBillId());
            billTourServingObjectEntity.setTsvoId(tsvoId);
            billTourServingObjectEntities.add(billTourServingObjectEntity);
            pivot.set(pivot.get() + 1);
        });
        billTourServingObjectEntities.forEach(billTourServingObjectEntity ->
                cMBillTourServingObjectService.createBillTourServingObject(billTourServingObjectEntity));
        if ("thanh-toan-momo".equals(paymentType.getPaytSlug())) {
            String orderInfo = "adadsd";
            BigDecimal price = billTotalMoney.subtract(billTotalSaleMoney);
            String notifyURL = String.format("http://127.0.0.1:5000/TourismManagement/%s", tourSlug);
            String returnURL = String.format("http://127.0.0.1:5000/TourismManagement/%s", tourSlug);
            return String.format("redirect:%s&billId=%d",
                    utilBeanFactory.getApplicationContext().getBean(MomoUtil.class)
                            .createOrder(price, orderInfo, notifyURL, returnURL).get("payUrl"),
                    createdBill.getBillId());
        }
        createdBill.setBillIsPaid(true);
        cMBillService.updateBill(createdBill);
        String directlyPaymentURL = "";
        return String.format("redirect:%s", directlyPaymentURL);
    }

    @PostMapping("/thanh-toan/cap-nhat")
    public void updateBillStaff(@RequestBody Map<String, String> body) {
        Integer billId = Integer.valueOf(body.get("billId"));
        BillEntity bill = cMBillService.getBillAsObj(billId);
        bill.setBillIsPaid(true);
        cMBillService.updateBill(bill);
    }
}
