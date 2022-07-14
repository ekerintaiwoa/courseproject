package com.mysubmittedprojec.courseproject.controller;


import com.mysubmittedprojec.courseproject.entity.User;
import com.mysubmittedprojec.courseproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

       @Autowired
      private UserService userService ;



         @PostConstruct
        public void initRolesandUsers(){

             userService.initRolesAndUser();

        }




        @PostMapping({"/registerNewUser"})
     public User registerNewUser(@RequestBody User user){

        return  userService.registerNewUser(user) ;



     }


         @GetMapping({"/admin"})
         @PreAuthorize("hasRole('admin')")
         public  String  adminArea(){


             return("This url is only acessible by Admins");


         }


         @GetMapping({"/users"})
         @PreAuthorize("hasRole('user')")
        public String userArea(){

             return("This url is only acessible by users");


        }









 }
