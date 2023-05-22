package com.example.springbootjpa.repository;

import com.example.springbootjpa.model.UserDetail;
import com.example.springbootjpa.param.UserDetailParam;
import com.example.springbootjpa.service.UserDetailService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Jpa规范测试
 */
@SpringBootTest
public class JpaSpecificationTests {

    @Resource
    private UserDetailService userDetailService;

    //    TODO:根据条件，分页查询，并返回实体
    @Test
    public void testFindByCondition() {
        int page = 0, size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        UserDetailParam param = new UserDetailParam();
        param.setIntroduction("程序员");
        param.setMinAge(10);
        param.setMaxAge(30);
        Page<UserDetail> page1 = userDetailService.findByCondition(param, pageable);
        for (UserDetail userDetail : page1) {
            System.out.println("userDetail: " + userDetail.toString());
        }
    }
}
