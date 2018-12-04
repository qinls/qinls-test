package com.qinlsspringboot.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2018/6/28.
 */
public class SessionUtil {


        /**
         * SpringMvc下获取request
         *
         * @return
         */
        public static HttpServletRequest getRequest() {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request;

        }
        /**
         * SpringMvc下获取session
         *
         * @return
         */
        public static HttpSession getSession() {
            HttpSession session = getRequest().getSession();
            return session;

        }

}
