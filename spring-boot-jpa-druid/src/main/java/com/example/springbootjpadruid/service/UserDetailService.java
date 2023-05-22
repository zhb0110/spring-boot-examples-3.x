package com.example.springbootjpadruid.service;

import com.example.springbootjpadruid.model.UserDetail;
import com.example.springbootjpadruid.param.UserDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDetailService {
    public Page<UserDetail> findByCondition(UserDetailParam detailParam, Pageable pageable);
}
