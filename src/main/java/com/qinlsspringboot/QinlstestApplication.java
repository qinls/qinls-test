package com.qinlsspringboot;

import com.qinlsspringboot.enums.YesOrNoEnums;
import com.qinlsspringboot.model.User;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
//@MapperScan("com.qinlsspringboot.mapper")//将项目中对应的mapper类的路径加进来就可以了
//在启动类上增加@EnableConfigurationProperties注解，激活自定义的配置类(重要) www
@EnableConfigurationProperties

//@PropertySource(value = {"classpath:/spring/*.properties","classpath:/spring/abc.properties"},
//applicationT ,"classpath:spring/user.properties"
//测试
@PropertySource(value = {"classpath:spring/applicationT.properties","classpath:spring/user.properties"},
        ignoreResourceNotFound = true, encoding = "utf-8")

//定时任务注解
@EnableScheduling
@Slf4j
public class QinlstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QinlstestApplication.class, args);
        log.info("字符串相等：{}", "Y".equals(YesOrNoEnums.Y.name()));

        log.info("字符串：{}", YesOrNoEnums.Y);

        User user = new User();
        User user2 = new User();
        user2.setPhone("25545");
        user = user2;


        log.info("是否为null:{}", user.equals(user2));
        log.info("是否为null:{}", user2.hashCode());
        log.info("是否为null:{}", user.getPhone());


        List<User> preProd = new ArrayList<>();
        log.info("是否为空：{}", CollectionUtils.isEmpty(preProd));


    }
}
