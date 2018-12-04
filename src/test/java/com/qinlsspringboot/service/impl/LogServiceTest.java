package com.qinlsspringboot.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2018/6/26.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTest {

    @Autowired
    LogService logService;

    @Test
    public void annoTest(){
        logService.log();
        logService.getLogId(33L);
    }
}
