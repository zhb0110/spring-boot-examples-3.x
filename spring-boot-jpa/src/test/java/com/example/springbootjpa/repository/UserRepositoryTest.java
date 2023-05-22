package com.example.springbootjpa.repository;

import com.example.springbootjpa.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    // 薪资用户
    @Test
    public void testSave() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa", "aa123456", "aa@126.com", "aa", formattedDate));
        userRepository.save(new User("bb", "bb123456", "bb@126.com", "bb", formattedDate));
        userRepository.save(new User("cc", "cc123456", "cc@126.com", "cc", formattedDate));

//		Assert.assertEquals(3, userRepository.findAll().size());
//		Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "bb@126.com").getNickName());
//		userRepository.delete(userRepository.findByUserName("aa"));
    }


    // 删除保存并查询剩余结果
    @Test
    public void testBaseQuery() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        User user = new User("ff", "ff123456", "ff@126.com", "ff", formattedDate);
        userRepository.findAll();
        userRepository.findById(3L);
        userRepository.save(user);
        user.setId(2L);
        userRepository.delete(user);
        userRepository.count();
        userRepository.existsById(3L);
    }

    // 删除并根据条件查询
    @Test
    public void testCustomSql() {
        userRepository.modifyById("neo", 3L);
        userRepository.deleteById(3L);
        userRepository.findByEmail("ff@126.com");
    }


    // 分页查询用户
    @Test
    public void testPageQuery() {
        int page = 1, size = 2;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        userRepository.findALL(pageable);
        userRepository.findByNickName("aa", pageable);
    }
}