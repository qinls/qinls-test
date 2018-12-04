package com.qinlsspringboot.test;

import com.qinlsspringboot.util.SessionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/6/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionTest {

    @Test
    public void testSession(){
        HttpSession session = SessionUtil.getSession();
        session.setAttribute("wo",1244);
    }

    @Test
    public void t1(){
        testSession();
        HttpSession session = SessionUtil.getSession();
        System.out.println(session);
    }



}
