package com.ou.customer.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ou.common.services.CMAccountService;
import com.ou.common.services.CMRoleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.AccountEntity;
import com.ou.pojos.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping(path = "/khach-hang/dang-ki-tai-khoan")
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
                                MultipartFile accAvatar) {
        accountEntity.setAccUsername(httpServletRequest.getParameter("accUsername"));
        accountEntity.setAccPassword(passwordEncoder.encode(httpServletRequest.getParameter("accPassword")));
        accountEntity.setAccFirstName(httpServletRequest.getParameter("accFirstName"));
        accountEntity.setAccLastName(httpServletRequest.getParameter("accLastName"));
        accountEntity.setAccSex(Byte.valueOf(httpServletRequest.getParameter("accSex")));
        accountEntity.setAccIdCard(httpServletRequest.getParameter("accIdCard"));
        accountEntity.setAccPhoneNumber(httpServletRequest.getParameter("accPhoneNumber"));
        accountEntity.setAccDateOfBirth(Timestamp.valueOf(httpServletRequest.getParameter("accDateOfBirth")));
        if (!accAvatar.isEmpty()) {
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
    public String getSignUpView() {
        return "c-sign-up";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> signUp(HttpServletRequest httpServletRequest,
                                                    @RequestParam(name = "accAvatar", required = false) MultipartFile accAvatar)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        AccountEntity account = pojoBeanFactory.getApplicationContext().getBean(AccountEntity.class);
        RoleEntity role = cMRoleService.getRoleAsObj("khach-hang");
        setAccountInfo(account, role, httpServletRequest, accAvatar);
        boolean createdResult = cMAccountService.createAccount(account);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }
}
