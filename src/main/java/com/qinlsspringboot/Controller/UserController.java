package com.qinlsspringboot.Controller;

import com.qinlsspringboot.model.User;
import com.qinlsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2018/5/7.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    public Object list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping(value = "/ggg", produces = {"application/json;charset=UTF-8"})
    public Object fffid() {

        return userService.selectByPrimaryKey(1);
    }
}