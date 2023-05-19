package com.example.springbootmybatisxmlmulidatasource.mapper.test1;

import com.example.springbootmybatisxmlmulidatasource.enums.UserSexEnum;
import com.example.springbootmybatisxmlmulidatasource.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class User1MapperTest {

    @Autowired
    private User1Mapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("cc", "b123456", UserSexEnum.WOMAN));

        System.out.println(userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
        if (users == null || users.size() == 0) {
            System.out.println("is null");
        } else {
            System.out.println(users.size());
        }
    }


    @Test
    public void testUpdate() throws Exception {
        Long id = 30l;
        User user = userMapper.getOne(id);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
        Assert.assertTrue(("neo".equals(userMapper.getOne(id).getNickName())));
    }
}