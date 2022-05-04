package com.ou.common.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.CMAccountService;
import com.ou.common.services.CMRoleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import com.ou.utils.MailUtil;
import com.ou.utils.OTPUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping(path = "/dang-ki-tai-khoan")
public class CSignUpController {

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
        accountEntity.setAccPassword(passwordEncoder.encode(httpServletRequest.getParameter("accPassword")));
        accountEntity.setAccFirstName(httpServletRequest.getParameter("accFirstName"));
        accountEntity.setAccLastName(httpServletRequest.getParameter("accLastName"));
        accountEntity.setAccSex(Byte.valueOf(httpServletRequest.getParameter("accSex")));
        accountEntity.setAccIdCard(httpServletRequest.getParameter("accIdCard"));
        accountEntity.setAccPhoneNumber(httpServletRequest.getParameter("accPhoneNumber"));
        Timestamp dob = utilBeanFactory.getApplicationContext().getBean("emptyTimeStamp", Timestamp.class);
        dob.setTime(utilBeanFactory.getApplicationContext().getBean(SimpleDateFormat.class).parse(
                httpServletRequest.getParameter("accDateOfBirth") + " 00:00:00").getTime());
        accountEntity.setAccDateOfBirth(dob);
        if (accAvatar != null && !accAvatar.isEmpty()) {
            try {
                Cloudinary cloudinary = utilBeanFactory.getApplicationContext().getBean(Cloudinary.class);
                Map url = cloudinary.uploader().upload(accAvatar.getBytes(),
                        ObjectUtils.asMap(
                                "resource_type", "auto",
                                "folder", "account"));
                accountEntity.setAccAvatar((String) url.get("secure_url"));
            } catch (IOException ignored) {
                accountEntity.setAccAvatar("https://res.cloudinary.com/ou-project/image/upload/v1650636461/account/defaultAvatar_bmgisb.png");
            }
        } else
            accountEntity.setAccAvatar("https://res.cloudinary.com/ou-project/image/upload/v1650636461/account/defaultAvatar_bmgisb.png");

        accountEntity.setRoleId(roleEntity.getRoleId());
    }


    // get
    @GetMapping()
    public String getSignUpView() {
        return "cm-sign-up";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signUp(HttpServletRequest httpServletRequest,
                         @RequestParam(name = "accAvatar", required = false) MultipartFile accAvatar) throws UnsupportedEncodingException, ParseException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        AccountEntity account = pojoBeanFactory.getApplicationContext().getBean(AccountEntity.class);
        RoleEntity role = cMRoleService.getRoleAsObj("khach-hang");
        setAccountInfo(account, role, httpServletRequest, accAvatar);
        boolean createdResult = cMAccountService.createAccount(account);
        if (createdResult)
            return "redirect:/auth/dang-nhap";
        return "redirect:/dang-ki-tai-khoan";
    }

    @PostMapping("/otp")
    public ResponseEntity<JSONObject> getOTP(@RequestBody Map<String, String> body) throws MessagingException {
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        String otp = utilBeanFactory.getApplicationContext().getBean(OTPUtil.class).generateOTP();
        jsonObject.put("otp", otp);
        MailUtil mailUtil = utilBeanFactory.getApplicationContext().getBean(MailUtil.class);
        String subject ="Xác nhận đăng kí tài khoản OU TOUR ";
        String content=String.format("Mã xác nhận đăng kí tài khoản của bạn là: %s", otp);
//        mailUtil.sendMail(body.get("email"), subject, content);
        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }

}
