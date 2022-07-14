package com.mysubmittedprojec.courseproject.services;


import com.mysubmittedprojec.courseproject.dao.RoleDao;
import com.mysubmittedprojec.courseproject.dao.UserDAO;
import com.mysubmittedprojec.courseproject.entity.Role;
import com.mysubmittedprojec.courseproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

     @Autowired
    private UserDAO userDAO ;


      @Autowired
     private PasswordEncoder passwordEncoder ;

     @Autowired
     private RoleDao roleDao ;
    public User registerNewUser(User user){

        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));


      return   userDAO.save(user) ;


     }


     public  void initRolesAndUser(){

        // we create two different roles , for admin and also for user
       Role  adminRole =  new Role() ;

       adminRole.setRoleName("admin");
       adminRole.setRoleDescription("Admin Role") ;
       roleDao.save(adminRole) ;




         Role  userRole =  new Role() ;

         userRole.setRoleName("user");
         userRole.setRoleDescription("Default role for newly created user") ;
         roleDao.save(userRole) ;



          // we create two users , and givethem user and admin and useroles respectively
         User adminUser   = new User() ;
         adminUser.setUserFirstName("admin") ;
         adminUser.setUserLastName("admin");
         adminUser.setUserName("admin123");
         adminUser.setUserPassword(getEncodedPassword("admin@pass"));
         Set<Role> adminRoles = new HashSet<>();
         adminRoles.add(adminRole);
         adminUser.setRole(adminRoles);
         userDAO.save(adminUser);


     /*    User user   = new User() ;
         user.setUserFirstName("user") ;
         user.setUserLastName("user");
         user.setUserName("user123");
         user.setUserPassword(getEncodedPassword("user@pass"));
         Set<Role> userRoles = new HashSet<>();
         userRoles.add(userRole);
         user.setRole(userRoles);
         userDAO.save(user);

      */





     }


      public String getEncodedPassword(String password){

        return passwordEncoder.encode(password);


      }






}
