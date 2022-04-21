package com.ou.pojos;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Role", schema = "TourismManagement")
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id")
    private int roleId;
    @Basic
    @Column(name = "role_name")
    private String roleName;
    @Basic
    @Column(name = "role_slug")
    private String roleSlug;
    @Basic
    @Column(name = "role_is_active")
    private byte roleIsActive;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSlug() {
        return roleSlug;
    }

    public void setRoleSlug(String roleSlug) {
        this.roleSlug = roleSlug;
    }

    public byte getRoleIsActive() {
        return roleIsActive;
    }

    public void setRoleIsActive(byte roleIsActive) {
        this.roleIsActive = roleIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId && roleIsActive == that.roleIsActive && Objects.equals(roleName, that.roleName) && Objects.equals(roleSlug, that.roleSlug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleSlug, roleIsActive);
    }
}
