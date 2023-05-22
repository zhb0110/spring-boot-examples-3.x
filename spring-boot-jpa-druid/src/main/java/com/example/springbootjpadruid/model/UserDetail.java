package com.example.springbootjpadruid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserDetail {

    // TODO:自增长
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true, length = 30)
    private Long userId;
    private Integer age;
    private String realName;
    private String status;
    // 爱好
    private String hobby;
    // 介绍
    private String introduction;
    private String lastLoginIp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", age=" + age +
                ", realName='" + realName + '\'' +
                ", status='" + status + '\'' +
                ", hobby='" + hobby + '\'' +
                ", introduction='" + introduction + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
