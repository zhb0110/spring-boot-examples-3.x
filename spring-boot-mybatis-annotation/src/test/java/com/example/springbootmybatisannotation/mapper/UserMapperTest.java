package com.example.springbootmybatisannotation.mapper;

import com.example.springbootmybatisannotation.enums.UserSexEnum;
import com.example.springbootmybatisannotation.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// TODO:@RunWith运行识别环境的，但是在idea中自动识别了，太方便，以至于可以不用
//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aa1", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bb1", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("cc1", "b123456", UserSexEnum.WOMAN));

        //  TODO:在pom中特殊增加依赖，就为了这个
        // 作用：1. 如果两者一致, 程序继续往下运行. 2. 如果两者不一致, 中断测试方法, 抛出异常信息
        // TODO:库中得没数据，否则和数据量不符
        Assert.assertEquals(3, userMapper.getAll().size());
    }

    @Test
    public void testQuery() throws Exception {
        List<User> users = userMapper.getAll();
        System.out.println(users.toString());
    }


    @Test
    public void testUpdate() throws Exception {
        // TODO:需使用库中用户id，会设置个昵称
        User user = userMapper.getOne(34l);
        System.out.println(user.toString());
        user.setNickName("neo");
        userMapper.update(user);
        // 查询昵称是否已经修改
        Assert.assertTrue(("neo".equals(userMapper.getOne(34l).getNickName())));
    }
}
