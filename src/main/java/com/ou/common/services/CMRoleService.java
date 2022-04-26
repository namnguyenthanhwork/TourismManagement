package com.ou.common.services;

import com.ou.pojos.RoleEntity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public interface CMRoleService {
    JSONArray getRoles(Integer pageIndex);

    RoleEntity getRoleAsObj(String roleSlug);

    JSONObject getRoleAsJsonObj(String roleSlug);

    boolean createRole(RoleEntity roleEntity);

    boolean updateRole(RoleEntity roleEntity);

    boolean deleteRole(RoleEntity roleEntity);
}
