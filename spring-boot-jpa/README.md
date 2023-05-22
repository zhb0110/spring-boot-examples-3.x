## spring-boot-spa

## 初始化与启动

由于配置了：spring.jpa.properties.hibernate.hbm2ddl.auto=create
或者这样：spring.jpa.hibernate.ddl-auto=update 可以根据实体@Entity自动创建表

## 分页查询 Demo

Page<UserDetail> page1 = userDetailService.findByCondition(param, pageable);

## jpa接口默认继承JpaRepository接口即可默认使用增删改查等内置的方法

## 其他则有时间测试吧

