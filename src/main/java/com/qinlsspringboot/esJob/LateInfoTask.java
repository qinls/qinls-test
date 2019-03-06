package com.qinlsspringboot.esJob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.qinlsspringboot.model.User;
import com.qinlsspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by qinls on 2019/3/5.
 */
@Slf4j
@Component
//springboot整合主要就是注解了。这里指定一下相关配偶。服务链接到zk上。 控制台配置之后就看得到啦，之后也可以在控制台上手动修改一些相关配置
@ElasticSimpleJob(cron = "0/3 * * * * ? *", jobName = "lateInfoTask", shardingTotalCount = 2, jobParameter = "测试参数", shardingItemParameters = "0=A,1=B")

public class LateInfoTask implements SimpleJob {

    @Autowired
    private UserService userService;


    @Override
    public void execute(ShardingContext shardingContext) {
        List<User> allUser = userService.findAllUser(1, 10);
        for (User user : allUser) {
            log.info("对象：{}", user);
        }

        System.out.println(new Date() + " job名称 = " + shardingContext.getJobName()
                + "分片数量" + shardingContext.getShardingTotalCount()
                + "当前分区" + shardingContext.getShardingItem()
                + "当前分区名称" + shardingContext.getShardingParameter()
                + "当前自定义参数" + shardingContext.getJobParameter() + "============start=================");

//        原文：https://blog.csdn.net/u013018994/article/details/76212229

    }
}
