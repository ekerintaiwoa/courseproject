package com.mysubmittedprojec.courseproject.dao;


import com.mysubmittedprojec.courseproject.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO  extends CrudRepository<User,String> {




}
