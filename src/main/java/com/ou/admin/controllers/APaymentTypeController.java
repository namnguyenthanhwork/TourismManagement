package com.ou.admin.controllers;

import com.ou.common.services.CMPaymentTypeService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.PaymentTypeEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/hinh-thuc-thanh-toan")
public class APaymentTypeController {

    @Autowired
    private CMPaymentTypeService cMPaymentTypeService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getPaymentTypesView() {
        return "a-payment-type";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getPaymentTypesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray paymentTypes = cMPaymentTypeService.getPaymentTypes(pageIndex);
        return new ResponseEntity<>(paymentTypes, paymentTypes.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getPaymentTypeCreatedView() {
        return "a-payment-type-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createPaymentType(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        PaymentTypeEntity paymentType = pojoBeanFactory.getApplicationContext().getBean(PaymentTypeEntity.class);
        paymentType.setPaytName(httpServletRequest.getParameter("paytName"));
        boolean createdResult = cMPaymentTypeService.createPaymentType(paymentType);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{paytSlug}")
    public String getPaymentTypeEditedView(@PathVariable String paytSlug) {
        return "a-payment-type-updated";
    }

    @GetMapping("/{paytSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getPaymentTypeDetail(@PathVariable String paytSlug) {
        JSONObject paymentType = cMPaymentTypeService.getPaymentTypeAsJsonObj(paytSlug);
        if (paymentType == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(paymentType, HttpStatus.OK);
    }

    @RequestMapping(value = "/{paytSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updatePaymentType(@PathVariable String paytSlug,
                                                        HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        PaymentTypeEntity paymentType = cMPaymentTypeService.getPaymentTypeAsObj(paytSlug);
        if (paymentType == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        paymentType.setPaytName(httpServletRequest.getParameter("paytName"));
        boolean updateResult = cMPaymentTypeService.updatePaymentType(paymentType);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{paytSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deletePaymentType(@PathVariable String paytSlug) {
        PaymentTypeEntity paymentType = cMPaymentTypeService.getPaymentTypeAsObj(paytSlug);
        if (paymentType == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMPaymentTypeService.deletePaymentType(paymentType);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
