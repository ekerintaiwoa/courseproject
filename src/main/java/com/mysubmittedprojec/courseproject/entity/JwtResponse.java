package com.mysubmittedprojec.courseproject.entity;




public class JwtResponse {


    private  User user ;

    private String jwtToken ;


    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
}
