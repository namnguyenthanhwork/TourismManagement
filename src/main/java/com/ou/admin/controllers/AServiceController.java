package com.ou.admin.controllers;

import com.ou.common.services.CMServiceService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.ServiceEntity;
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
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/dich-vu")
public class AServiceController {

    @Autowired
    private CMServiceService cMServiceService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getServicesView() {
        return "a-service";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getServicesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray services = cMServiceService.getServices(pageIndex);
        return new ResponseEntity<>(services, services.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getServicePageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMServiceService.getServiceAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // create
    @GetMapping("/tao-moi")
    public String getServiceCreatedView() {
        return "a-service-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createService(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ServiceEntity serviceEntity = pojoBeanFactory.getApplicationContext().getBean(ServiceEntity.class);
        serviceEntity.setServTitle(httpServletRequest.getParameter("servTitle"));
        serviceEntity.setServContent(httpServletRequest.getParameter("servContent"));
        boolean createdResult = cMServiceService.createService(serviceEntity);
        if (createdResult)
            return "redirect:/quan-tri-vien/dich-vu";
        return "redirect:/quan-tri-vien/dich-vu/tao-moi?error=1";
    }

    // update
    @GetMapping("/{servSlug}")
    public String getServiceEditedView(@PathVariable String servSlug) {
        return "a-service-updated";
    }

    @GetMapping("/{servSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getServiceDetail(@PathVariable String servSlug) {
        JSONObject service = cMServiceService.getServiceAsJsonObj(servSlug);
        if (service == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

    @RequestMapping(value = "/{servSlug}", method = RequestMethod.POST)
    public String updateService(@PathVariable String servSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        ServiceEntity service = cMServiceService.getServiceAsObj(servSlug);
        if (service == null)
            return String.format("redirect:/quan-tri-vien/dich-vu/%s?error=1", servSlug);
        service.setServTitle(httpServletRequest.getParameter("servTitle"));
        service.setServContent(httpServletRequest.getParameter("servContent"));
        boolean updateResult = cMServiceService.updateService(service);
        if (updateResult)
            return "redirect:/quan-tri-vien/dich-vu";
        return String.format("redirect:/quan-tri-vien/dich-vu/%s?error=1", servSlug);
    }

    // delete
    @RequestMapping(value = "/{servSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteService(@PathVariable String servSlug) {
        ServiceEntity service = cMServiceService.getServiceAsObj(servSlug);
        if (service == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMServiceService.deleteService(service);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
