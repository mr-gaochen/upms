package com.eric.grace.upms.common.constant;


import io.swagger.models.auth.In;

/**
 * SysConstant: 系统常量类
 *
 * @author: MrServer
 * @since: 2018/4/19 上午10:26
 */
public class SysConstant {


    /** 超级管理员ID */
    public static final String SUPER_ADMIN = "0";

    public final static String DEFAULT_DEL_FLAG_NO = "N";


    public final static String DEFAULT_PASSWORD = "123456";




    public enum MenuType {
        /**
         * 目录
         */
        CATALOG("0"),
        /**
         * 菜单
         */
        MENU("1"),
        /**
         * 按钮
         */
        BUTTON("2");

        private String value;

        private MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }



    // 用户状态
    public enum UserStatus {
        /**
         * 正常
         */
        NORMAL(1),
        /**
         * 菜单
         */
        DENY(0);

        private Integer value;

        private UserStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }






}