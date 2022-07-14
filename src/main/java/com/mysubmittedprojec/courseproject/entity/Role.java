package com.mysubmittedprojec.courseproject.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    private String  roleName ;
    private String roleDescription ;



    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription(String admin_role) {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
