-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE T_USER (
                        ID bigint NOT NULL ,
                        USERNAME VARCHAR(255) NOT NULL COMMENT '用户名' ,
                        PASSWD VARCHAR(255) NOT NULL COMMENT '密码' ,
                        CREATE_TIME datetime NULL COMMENT '创建时间' ,
                        STATUS CHAR(1) NOT NULL COMMENT '是否有效 1:有效 0:锁定'
);
-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO T_USER VALUES ('2', 'test', '7a38c13ec5e9310aed731de58bbc4214', '2017-11-19 17:20:21', '0');
INSERT INTO T_USER VALUES ('1', 'mrbird', '42ee25d1e43e9f57119a00d0a39e5250', '2017-11-19 10:52:48', '1');
-- ----------------------------
-- Primary Key structure for table T_USER
-- ----------------------------
ALTER TABLE T_USER ADD PRIMARY KEY (ID);