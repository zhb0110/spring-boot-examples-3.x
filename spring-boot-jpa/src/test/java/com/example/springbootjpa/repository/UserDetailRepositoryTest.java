package com.example.springbootjpa.repository;

import com.example.springbootjpa.model.Address;
import com.example.springbootjpa.model.UserDetail;
import com.example.springbootjpa.model.UserInfo;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailRepositoryTest {

    @Resource
    private AddressRepository addressRepository;
    @Resource
    private UserDetailRepository userDetailRepository;

    //    TODO:新增地址
    @Test
    public void testSaveAddress() {
        Address address = new Address();
        address.setUserId(1L);
        address.setCity("北京");
        address.setProvince("北京");
        address.setStreet("分钟寺");
        addressRepository.save(address);
    }

    // 薪资用户属性
    @Test
    public void testSaveUserDetail() {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormat.format(date);
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(3L);
        userDetail.setHobby("吃鸡游戏");
        userDetail.setAge(28);
        userDetail.setIntroduction("一个爱玩的人");
        userDetailRepository.save(userDetail);
    }

    // TODO:查询用户
    @Test
    public void testUserInfo() {
        List<UserInfo> userInfos = userDetailRepository.findUserInfo("钓鱼");
        for (UserInfo userInfo : userInfos) {
            System.out.println("userInfo: " + userInfo.getUserName() + "-" + userInfo.getEmail() + "-" + userInfo.getHobby() + "-" + userInfo.getIntroduction());
        }
    }
}