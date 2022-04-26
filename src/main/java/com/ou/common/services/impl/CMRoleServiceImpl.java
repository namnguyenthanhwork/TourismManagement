package com.ou.common.services.impl;

import com.ou.common.repositories.CMRoleRepository;
import com.ou.common.services.CMRoleService;
import com.ou.configs.BeanFactoryConfig;
import com.ou.pojos.RoleEntity;
import com.ou.utils.SlugUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMRoleServiceImpl implements CMRoleService {

    @Autowired
    private CMRoleRepository cMRoleRepository;
    @Autowired
    private BeanFactoryConfig.UtilBeanFactory utilBeanFactory;

    @Override
    public JSONArray getRoles(Integer pageIndex) {
        List<Object[]> roles = cMRoleRepository.getRoles(pageIndex);
        JSONArray jsonArray = utilBeanFactory.getApplicationContext().getBean(JSONArray.class);
        roles.forEach(role -> {
            JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
            jsonObject.put("roleId", role[0]);
            jsonObject.put("roleName", role[1]);
            jsonObject.put("roleSlug", role[2]);
            jsonArray.add(jsonObject);
        });
        return jsonArray;
    }


    @Override
    public RoleEntity getRoleAsObj(String roleSlug) {
        if (roleSlug == null || roleSlug.trim().length() == 0)
            return null;
        return cMRoleRepository.getRole(roleSlug);
    }

    @Override
    public JSONObject getRoleAsJsonObj(String roleSlug) {
        if (roleSlug == null || roleSlug.trim().length() == 0)
            return null;
        JSONObject jsonObject = utilBeanFactory.getApplicationContext().getBean(JSONObject.class);
        RoleEntity role = cMRoleRepository.getRole(roleSlug.trim());
        if (role == null)
            return null;
        jsonObject.put("roleId", role.getRoleId());
        jsonObject.put("roleName", role.getRoleName());
        jsonObject.put("roleSlug", role.getRoleSlug());
        return jsonObject;
    }

    @Override
    public boolean createRole(RoleEntity roleEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(roleEntity.getRoleName());
        roleEntity.setRoleSlug(slugUtil.getSlug());
        return cMRoleRepository.createRole(roleEntity);
    }

    @Override
    public boolean updateRole(RoleEntity roleEntity) {
        SlugUtil slugUtil = utilBeanFactory.getApplicationContext().getBean(SlugUtil.class);
        slugUtil.setSlug(roleEntity.getRoleName());
        roleEntity.setRoleSlug(slugUtil.getSlug());
        if(cMRoleRepository.getRole(roleEntity.getRoleSlug())!=null)
            return false;
        boolean updateResult;
        try {
            updateResult = cMRoleRepository.updateRole(roleEntity);
        } catch (Exception e) {
            updateResult = false;
        }
        return updateResult;
    }

    @Override
    public boolean deleteRole(RoleEntity roleEntity) {
        return cMRoleRepository.deleteRole(roleEntity);
    }


}
