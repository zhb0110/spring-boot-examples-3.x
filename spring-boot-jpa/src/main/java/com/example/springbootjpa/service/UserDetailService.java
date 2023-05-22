package com.example.springbootjpa.service;

import com.example.springbootjpa.model.UserDetail;
import com.example.springbootjpa.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
