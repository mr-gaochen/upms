package com.eric.grace.upms.common.constant;


/**
 * SysConstant: 系统常量类
 *
 * @author: MrServer
 * @since: 2018/4/19 上午10:26
 */
public class SysConstant {


    public final static String DEFAULT_DEL_FLAG_NO = "N";


    public final static String DEFAULT_PASSWORD = "123456";


    public enum UserStatus {
        /**
         * 冻结
         */
        FROZEN(-1),

    }


    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}