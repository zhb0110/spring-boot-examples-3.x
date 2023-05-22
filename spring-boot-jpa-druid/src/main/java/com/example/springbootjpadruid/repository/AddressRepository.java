package com.example.springbootjpadruid.repository;

import com.example.springbootjpadruid.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO:继承 JpaRepository接口(该接口本身已经继承了CrudRepository接口)
 */
// 地址仓库
public interface AddressRepository extends JpaRepository<Address, Long> {


}
