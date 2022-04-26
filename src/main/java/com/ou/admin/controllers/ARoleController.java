package com.ou.admin.controllers;

import com.ou.common.services.CMRoleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.RoleEntity;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
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
@RequestMapping(path = "/quan-tri-vien/vai-tro")
public class ARoleController {

    @Autowired
    private CMRoleService cMRoleService;

    @Autowired
    private BeanFactoryConfig.PojoBeanFactory pojoBeanFactory;

    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    // get
    @GetMapping()
    public String getRolesView() {
        return "a-role";
    }

    @GetMapping("/thong-tin")
    public ResponseEntity<JSONArray> getRolesInfo(@RequestParam Map<String, String> params) {
        Integer pageIndex = null;
        try {
            pageIndex = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException ignored) {
        }
        JSONArray roles = cMRoleService.getRoles(pageIndex);
        return new ResponseEntity<>(roles, roles.size() > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    // create
    @GetMapping("/tao-moi")
    public String getRoleCreatedView() {
        return "a-role-created";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createRole(HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        RoleEntity roleEntity = pojoBeanFactory.getApplicationContext().getBean(RoleEntity.class);
        roleEntity.setRoleName(httpServletRequest.getParameter("roleName"));
        boolean createdResult = cMRoleService.createRole(roleEntity);
        return new ResponseEntity<>(createdResult ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    // update
    @GetMapping("/{roleSlug}")
    public String getRoleEditedView(@PathVariable String roleSlug) {
        return "a-role-updated";
    }

    @GetMapping("/{roleSlug}/chinh-sua")
    public ResponseEntity<JSONObject> getRoleDetail(@PathVariable String roleSlug) {
        JSONObject role = cMRoleService.getRoleAsJsonObj(roleSlug);
        if (role == null)
            return new ResponseEntity<>(utilBeanFactory.getApplicationContext()
                    .getBean(JSONObject.class), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @RequestMapping(value = "/{roleSlug}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> updateRole(@PathVariable String roleSlug, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        RoleEntity role = cMRoleService.getRoleAsObj(roleSlug);
        if (role == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        role.setRoleName(httpServletRequest.getParameter("roleName"));
        boolean updateResult=cMRoleService.updateRole(role);
        return new ResponseEntity<>(updateResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    // delete
    @RequestMapping(value = "/{roleSlug}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable String roleSlug) {
        RoleEntity role = cMRoleService.getRoleAsObj(roleSlug);
        if (role == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        boolean deleteResult = cMRoleService.deleteRole(role);
        return new ResponseEntity<>(deleteResult ? HttpStatus.OK : HttpStatus.CONFLICT);
    }
}

