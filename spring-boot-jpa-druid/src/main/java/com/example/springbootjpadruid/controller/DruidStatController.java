package com.example.springbootjpadruid.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.example.springbootjpadruid.model.UserDetail;
import com.example.springbootjpadruid.param.UserDetailParam;
import com.example.springbootjpadruid.service.UserDetailService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DruidStatController {

    @Resource
    private UserDetailService userDetailService;

    @GetMapping("/getUserByParam")
    public Page<UserDetail> get() {
        int page = 0, size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        UserDetailParam param = new UserDetailParam();
        param.setIntroduction("程序员");
        param.setMinAge(10);
        param.setMaxAge(30);
        Page<UserDetail> page1 = userDetailService.findByCondition(param, pageable);
        return page1;
    }

    @GetMapping("/druid/stat")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }

}
