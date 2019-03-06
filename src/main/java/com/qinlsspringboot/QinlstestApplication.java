package com.qinlsspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
//@MapperScan("com.qinlsspringboot.mapper")//将项目中对应的mapper类的路径加进来就可以了
//在启动类上增加@EnableConfigurationProperties注解，激活自定义的配置类(重要) www
@EnableConfigurationProperties

//@PropertySource(value = {"classpath:/spring/*.properties","classpath:/spring/abc.properties"},
//applicationT ,"classpath:spring/user.properties"

@PropertySource(value = {"classpath:spring/applicationT.properties","classpath:spring/user.properties"},
        ignoreResourceNotFound = true, encoding = "utf-8")

//定时任务注解
@EnableScheduling
public class QinlstestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QinlstestApplication.class, args);
    }
}
