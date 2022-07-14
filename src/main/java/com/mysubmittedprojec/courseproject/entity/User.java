package com.mysubmittedprojec.courseproject.entity;


import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

     @Id
    private String userName;
    private String userPassword ;
    private String userFirstName ;
    private String userLastName ;




     // users can have many roles

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLE",joinColumns = { @JoinColumn( name ="USER_ID")},inverseJoinColumns = { @JoinColumn(name = "ROLE_ID")})
    private Set<Role> role ;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
