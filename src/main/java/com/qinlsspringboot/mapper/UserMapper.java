package com.qinlsspringboot.mapper;

import com.qinlsspringboot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //123
    //这个方式我自己加的
    List<User> selectAllUser();

    // 数组形式更新字段
    int updateAllById(Integer[] array);
    //la我即使我
    List<User> selectByOther(List<User> users);
}
