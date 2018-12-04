package com.qinlsspringboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/6/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {
    @Test
    public void updateAllById() throws Exception {
        Integer[] str = {1, 2, 3};
        userService.updateAllById(str);
    }

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void findAllUser() throws Exception {
        ;
        log.info("*************{}", userService.findAllUser(1, 2));
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

}