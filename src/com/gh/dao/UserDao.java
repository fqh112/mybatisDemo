package com.gh.dao;

import com.gh.entity.Login;

import java.util.List;

public interface UserDao {

    List<Login> findAll() throws Exception;

    void insertUser(Login login) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<Login> selectByName(String s) throws Exception;
}
