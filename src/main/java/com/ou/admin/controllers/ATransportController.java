package com.ou.admin.controllers;


import com.ou.common.services.CMTransportService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.TransportEntity;
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
@RequestMapping(path = "/quan-tri-vien/phuong-tien-di-chuyen")
public class ATransportController {

    @Autowired
    private CMTransportService cMTransportService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getTransportsView() {
        return "a-transport";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getTransportsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray transports = cMTransportService.getTransports(pageIndex);
        return new ResponseEntity<>(transports, transports.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getTransportCreatedView() {
        return "a-transport-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createTransport(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        TransportEntity transportEntity = pojoBeanFactory.getApplicationContext().getBean(TransportEntity.class);
        transportEntity.setTranName(httpServletRequest.getParameter("tranName"));
        boolean createdResult = cMTransportService.createTransport(transportEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{tranSlug}")
    public String getTransportEditedView(@PathVariable String tranSlug) {
        return "a-transport-updated";
    }

    @GetMapping("/{tranSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getTransportDetail(@PathVariable String tranSlug) {
        JSONObject transport = cMTransportService.getTransportAsJsonObj(tranSlug);
        if (transport == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(transport, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tranSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateTransport(@PathVariable String tranSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        TransportEntity transport = cMTransportService.getTransportAsObj(tranSlug);
        if (transport == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        transport.setTranName(httpServletRequest.getParameter("tranName"));
        boolean updateResult = cMTransportService.updateTransport(transport);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{tranSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteTransport(@PathVariable String tranSlug) {
        TransportEntity transport = cMTransportService.getTransportAsObj(tranSlug);
        if (transport == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMTransportService.deleteTransport(transport);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}
