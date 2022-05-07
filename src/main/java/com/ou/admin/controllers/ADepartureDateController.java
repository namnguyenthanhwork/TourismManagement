package com.ou.admin.controllers;

import com.ou.common.services.CMDepartureDateService;
import com.ou.common.services.CMFeatureService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.DepartureDateEntity;
import com.ou.pojos.FeatureEntity;
import com.ou.utils.PageUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/ngay-khoi-hanh")
public class ADepartureDateController {

    @Autowired
    private CMDepartureDateService cMDepartureDateService;

    @Autowired
    private CMFeatureService cMFeatureService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;


    // get
    @GetMapping()
    public String getDepartureDatesView() {
        return "a-departure-date";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getDepartureDatesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray departureDates = cMDepartureDateService.getDepartureDates(pageIndex);
        return new ResponseEntity<>(departureDates, departureDates.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getDepartureDatePageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMDepartureDateService.getDepartureDateAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }


    // create
    @GetMapping("/tao-moi")
    public String getDepartureDateCreatedView() {
        return "a-departure-date-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createDepartureDate(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        DepartureDateEntity departureDate = pojoBeanFactory.getApplicationContext().getBean(DepartureDateEntity.class);
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(httpServletRequest.getParameter("feaSlug"));
        Timestamp dptDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        dptDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class)
                .parse(httpServletRequest.getParameter("dptDate")+ " 00:00:00").getTime());
        departureDate.setDptDate(dptDate);
        departureDate.setFeaId(feature.getFeaId());
        boolean createdResult = cMDepartureDateService.createDepartureDate(departureDate);
        if (createdResult)
            return "redirect:/quan-tri-vien/ngay-khoi-hanh";
        return "redirect:/quan-tri-vien/ngay-khoi-hanh/tao-moi?error=1";
    }

    // update
    @GetMapping("/{dptId}")
    public String getDepartureDateEditedView(@PathVariable Integer dptId) {
        return "a-departure-date-updated";
    }

    @GetMapping("/{dptId}/chinh-sua")
    public ResponseEntity<JSONObject> getDepartureDateDetail(@PathVariable Integer dptId) {
        JSONObject departureDate = cMDepartureDateService.getDepartureDateAsJsonObj(dptId);
        if (departureDate == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(departureDate, HttpStatus.OK);
    }

    @RequestMapping(value = "/{dptId}", method = RequestMethod.POST)
    public String updateDepartureDate(@PathVariable Integer dptId, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDateAsObj(dptId);
        FeatureEntity feature = cMFeatureService.getFeatureAsObj(httpServletRequest.getParameter("feaSlug"));
        if (departureDate == null)
            return String.format("redirect:/quan-tri-vien/ngay-khoi-hanh/%d?error=1", dptId);
        Timestamp dptDate = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        dptDate.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class)
                .parse(httpServletRequest.getParameter("dptDate")+ " 00:00:00").getTime());
        departureDate.setDptDate(dptDate);
        departureDate.setFeaId(feature.getFeaId());
        boolean updateResult = cMDepartureDateService.updateDepartureDate(departureDate);
        if (updateResult)
            return "redirect:/quan-tri-vien/ngay-khoi-hanh";
        return String.format("redirect:/quan-tri-vien/ngay-khoi-hanh/%d?error=1", dptId);
    }

    // delete
    @RequestMapping(value = "/{dptId}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteDepartureDate(@PathVariable Integer dptId) {
        DepartureDateEntity departureDate = cMDepartureDateService.getDepartureDateAsObj(dptId);
        if (departureDate == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMDepartureDateService.deleteDepartureDate(departureDate);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
