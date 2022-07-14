package com.mysubmittedprojec.courseproject.services;

import com.mysubmittedprojec.courseproject.dao.UserDAO;
import com.mysubmittedprojec.courseproject.entity.JwtRequest;
import com.mysubmittedprojec.courseproject.entity.JwtResponse;
import com.mysubmittedprojec.courseproject.entity.User;
import com.mysubmittedprojec.courseproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {


    @Autowired
    private UserDAO userDAO ;


    @Autowired
    private JwtUtil jwtUtil ;


    @Autowired
    private AuthenticationManager authenticationManager ;


    public JwtResponse createJwtToken(JwtRequest  jwtRequest) throws Exception{


       String userName =  jwtRequest.getUserName() ;
         String userPassword= jwtRequest.getUserPassword() ;
         authenticate(userName,userPassword);

          UserDetails userDetails =  loadUserByUsername(userName) ;

        String newGeneratedToken = jwtUtil.generateToken(userDetails);
         User user=       userDAO.findById(userName).get() ;


       return new JwtResponse(user, newGeneratedToken)  ;


    }



    private void authenticate(String userName, String userPassword) throws Exception{

        try{
            // check error
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (DisabledException e){

          throw new Exception("user is Disabled");


        }  catch (BadCredentialsException e){

            throw new Exception("Bad credentials drom user");

        }





    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userDAO.findById(username).get();

        if (user != null) {
            // returns a user provider by spring security
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthority(user)
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }


    }


    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }







}
