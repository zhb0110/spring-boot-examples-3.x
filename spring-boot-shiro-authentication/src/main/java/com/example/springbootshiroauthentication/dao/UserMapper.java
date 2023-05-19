package com.example.springbootshiroauthentication.dao;

import com.example.springbootshiroauthentication.pojo.User;
import org.apache.ibatis.annotations.Mapper;

// TODO:mybatis两种方式，但这里必须是接口interface
//  极简xml：MyBatis自动生成mapper和dao
//  纯注解：
@Mapper
public interface UserMapper {
    User findByUserName(String userName);
}
