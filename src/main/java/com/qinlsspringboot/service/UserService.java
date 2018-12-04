package com.qinlsspringboot.service;

import com.qinlsspringboot.model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public interface UserService {

    int addUser(User user);

    User selectByPrimaryKey(Integer id);

    List<User> findAllUser(int pageNum, int pageSize);

    int updateAllById(Integer[] array);
}