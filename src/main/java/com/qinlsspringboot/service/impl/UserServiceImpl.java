package com.qinlsspringboot.service.impl;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinlsspringboot.common.utils.JsonUtils;
import com.qinlsspringboot.mapper.UserMapper;
import com.qinlsspringboot.model.User;
import com.qinlsspringboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/5/7.
 */
@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响
    @Autowired
    RedisTemplate redisTemplate;

    @Value("${USER_ID}")
    private String USER_ID;

    @Override
    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        // -----------------String类型数据操作 start--------------------
        //        参考网站 https://blog.csdn.net/fengyao1995/article/details/72794899

        User user = new User();

        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();

//        hashOperations.delete(USER_ID, id);
        // 查询缓存如果缓存中有直接响应结果
        // 加上try catch 即使是缓存异常也不会影响结果
        try {
            logger.info("缓存中是否存在:" + hashOperations.hasKey(USER_ID, id));
            if (hashOperations.hasKey(USER_ID, id)) {
                logger.info("从缓存中返回数据" + hashOperations.get(USER_ID, id));
                return (User) hashOperations.get(USER_ID, id);
            }
        } catch (Exception e) {
            logger.error("error 查询缓存异常");
            e.printStackTrace();
        }


        user = userMapper.selectByPrimaryKey(id);

        // -----------------hash数据类型操作 start------------------
        // 把数据存入缓存
        try {
            if (user != null) {
                Map map = new HashMap();
                map.put(id, user);
                hashOperations.putAll(USER_ID, map);
                System.out.println("值是" + USER_ID + ":" + JsonUtils.objectToJson(hashOperations.get(USER_ID, id)));
            }

        } catch (Exception e) {
            logger.error("user添加缓存错误");
            e.printStackTrace();
        }

        // -----------------hash数据类型操作 start------------------
//        redisTemplate.opsForValue().set("user", id);
//        System.out.println(redisTemplate.opsForValue().get("test4"));
//        redisTemplate.opsForHash().put("user", id, userMapper.selectByPrimaryKey(id));
//        System.out.println(redisTemplate.opsForHash().get("user", id));
//        System.out.println(redisTemplate.opsForHash().entries("user"));
        return user;
    }


    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectAllUser();

//        Page<User> userList = (Page<User>) userMapper.selectAllUser();
        PageInfo<User> appsPageInfo = new PageInfo<>(userList);

//        List pagelist = userList.getResult();

//        log.info("@@@@@@@@@@@@{}", appsPageInfo);

//        List<User> users = appsPageInfo.getList();
//        log.info("对象看啊可{}", pagelist);

        return userList;
    }


    /**
     * redis 测试类
     *
     * @param id
     * @return
     */
    public User testSelectByPrimaryKey(Integer id) {
        // -----------------String类型数据操作 start--------------------
        //        参考网站 https://blog.csdn.net/fengyao1995/article/details/72794899
        ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

        // String类型数据存储，不设置过期时间，永久性保存
        stringValueOperations.set("string1", "fiala");

        // String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
        stringValueOperations.set("string2", "fiala", 80, TimeUnit.SECONDS);

        String value1 = stringValueOperations.get("string1");
        String value2 = stringValueOperations.get("string2");
        // -----------------String类型数据操作 end--------------------


        // -----------------其他值类型数据操作 start--------------------
        User userDemo = new User();
        userDemo.setUserId(11);
        userDemo.setUserName("覃龙师");
        List<User> users = new ArrayList<User>();
        users.add(userDemo);

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 设置value为对象类型，且不设置过期时间，默认永久
        valueOperations.set("value1", userDemo);
        // 设置value为对象类型，设置过期时间为80秒，时间单位由TimeUnit控制
        valueOperations.set("value2", users, 10, TimeUnit.SECONDS);
        User user1 = (User) valueOperations.get("value1");
        List<User> list2 = (List<User>) valueOperations.get("value2");
        System.out.println(JsonUtils.objectToJson(user1));
        System.out.println(list2.toString());

        // -----------------hash数据类型操作 start------------------
        HashOperations<String, Object, Object> hashOperations = redisTemplate
                .opsForHash();
        Map map = new HashMap();
        map.put(id, userMapper.selectByPrimaryKey(id));
        hashOperations.putAll(USER_ID, map);
        System.out.println("值是" + USER_ID + ":" + JsonUtils.objectToJson(hashOperations.get("user", id)));
        // -----------------hash数据类型操作 start------------------

//        redisTemplate.opsForValue().set("user", id);
//        System.out.println(redisTemplate.opsForValue().get("test4"));
//        redisTemplate.opsForHash().put("user", id, userMapper.selectByPrimaryKey(id));
//        System.out.println(redisTemplate.opsForHash().get("user", id));
//        System.out.println(redisTemplate.opsForHash().entries("user"));
        return userMapper.selectByPrimaryKey(id);
    }



    public int updateAllById(Integer[] array){
        return userMapper.updateAllById(array);
    }

}