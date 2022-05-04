package com.ou.common.repositories;

import com.ou.pojos.RoleEntity;

import java.util.List;

public interface CMRoleRepository {
    List<Object[]> getRoles(Integer pageIndex);
    long getRoleAmount();

    RoleEntity getRole(String roleSlug);

    RoleEntity getRole(Integer roleId);

    boolean createRole(RoleEntity roleEntity);

    boolean updateRole(RoleEntity roleEntity);

    boolean deleteRole(RoleEntity roleEntity);
}
