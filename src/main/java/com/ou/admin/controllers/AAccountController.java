package com.ou.admin.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.CMAccountService;
import com.ou.common.services.CMRoleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import com.ou.utils.PageUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(path = "/quan-tri-vien/tai-khoan")
public class AAccountController {

    @Autowired
    private CMAccountService cMAccountService;

    @Autowired
    private CMRoleService cMRoleService;
    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private void setAccountInfo(AccountEntity accountEntity,
                                RoleEntity roleEntity,
                                HttpServletRequest httpServletRequest,
                                MultipartFile accAvatar) throws ParseException {
        accountEntity.setAccUsername(httpServletRequest.getParameter("accUsername"));
        if (httpServletRequest.getParameter("accPassword") != null)
            accountEntity.setAccPassword(passwordEncoder.encode(httpServletRequest.getParameter("accPassword")));
        accountEntity.setAccFirstName(httpServletRequest.getParameter("accFirstName"));
        accountEntity.setAccLastName(httpServletRequest.getParameter("accLastName"));
        accountEntity.setAccSex(Byte.valueOf(httpServletRequest.getParameter("accSex")));
        accountEntity.setAccIdCard(httpServletRequest.getParameter("accIdCard"));
        accountEntity.setAccPhoneNumber(httpServletRequest.getParameter("accPhoneNumber"));
        Timestamp dob = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        dob.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(httpServletRequest
                .getParameter("accDateOfBirth") + " 00:00:00").getTime());
        accountEntity.setAccDateOfBirth(dob);
        if (accAvatar!=null && !accAvatar.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(accAvatar.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "account"));
                accountEntity.setAccAvatar((String) url.get("secure_url"));
            } catch (IOException ignored) {
                if (accountEntity.getAccAvatar() == null && accountEntity.getAccAvatar().length() == 0)
                    accountEntity.setAccAvatar("https://res.cloudinary.com/ou-project/image/upload/v1650636461/account/defaultAvatar_bmgisb.png");
            }
        } else {
            if (accountEntity.getAccAvatar() == null && accountEntity.getAccAvatar().length() == 0)
                accountEntity.setAccAvatar("https://res.cloudinary.com/ou-project/image/upload/v1650636461/account/defaultAvatar_bmgisb.png");
        }
        accountEntity.setRoleId(roleEntity.getRoleId());
    }

    // get
    @GetMapping()
    public String getAccountsView() {
        return "a-account";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getAccountsInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("trang"));
        } catch (NumberFormatException ignored) {
        }
        String kw = params.get("kw");
        JSONArray accounts = cMAccountService.getAccounts(pageIndex, kw);
        return new ResponseEntity<>(accounts, accounts.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @GetMapping("/so-trang")
    public ResponseEntity<JSONObject> getAccountPageAmount(){
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        PageUtil pageUtil = utilBeanFactory.getApplicationContext().getBean(PageUtil.class);
        jsonObject.put("pageAmount",pageUtil.getPageAmount(cMAccountService.getAccountAmount()));
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

    // create
    @GetMapping("/tao-moi")
    public String getAccountCreatedView() {
        return "a-account-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createAccount(HttpServletRequest httpServletRequest,
                                @RequestParam(name = "accAvatar", required = false) MultipartFile accAvatar)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        AccountEntity account = pojoBeanFactory.getApplicationContext().getBean(AccountEntity.class);
        RoleEntity role = cMRoleService.getRoleAsObj(httpServletRequest.getParameter("roleSlug"));
        setAccountInfo(account, role, httpServletRequest, accAvatar);
        boolean createdResult = cMAccountService.createAccount(account);
        if (createdResult)
            return "redirect:/quan-tri-vien/tai-khoan";
        return "redirect:/quan-tri-vien/tai-khoan/tao-moi";
    }

    // update
    @GetMapping("/{accUsername}")
    public String getAccountEditedView(@PathVariable String accUsername) {
        return "a-account-updated";
    }

    @GetMapping("/{accUsername}/chinh-sua")
    public ResponseEntity<JSONObject> getAccountDetail(@PathVariable String accUsername) {
        JSONObject account = cMAccountService.getAccountAsJsonObj(accUsername);
        if (account == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accUsername}", method = RequestMethod.POST)
    public String updateAccount(@PathVariable String accUsername, HttpServletRequest httpServletRequest,
                                @RequestParam(name = "accAvatar", required = false) MultipartFile accAvatar)
            throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        AccountEntity account = cMAccountService.getAccountAsObj(accUsername);
        if (account == null)
            return String.format("redirect:/quan-tri-vien/tai-khoan/%s", accUsername);
        String password = account.getAccPassword();
        RoleEntity role = cMRoleService.getRoleAsObj(httpServletRequest.getParameter("roleSlug"));
        setAccountInfo(account, role, httpServletRequest, accAvatar);
        account.setAccPassword(password);
        boolean updateResult = cMAccountService.updateAccount(account);
        if (updateResult)
            return "redirect:/quan-tri-vien/tai-khoan";
        return String.format("redirect:/quan-tri-vien/tai-khoan/%s", accUsername);
    }

    // delete
    @RequestMapping(value = "/{accUsername}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable String accUsername) {
        AccountEntity account = cMAccountService.getAccountAsObj(accUsername);
        if (account == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMAccountService.deleteAccount(account);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

}
