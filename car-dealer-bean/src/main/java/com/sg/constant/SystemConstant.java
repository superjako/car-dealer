package com.sg.constant;

public class SystemConstant {

    /**
     * 用户登录TOKEN 缓存
     */
    public static final String CACHE_LOGIN_TOKEN = "tour:login:token:";

    /**
     * 初始化密码
     */
    public static final String START_PASSWORD = "123456";

    /**
     * 数据状态
     */
    public static final int DELETED = 0;
    public static final int NORMAL = 1;


    /**
     * 附件上传模块 1：发布车辆 2：注册商家 3：发表动态
     */
    public static final String SAVE_CAR = "1";
    public static final String SAVE_USER = "2";
    public static final String SAVE_TALK = "3";

    public static final class Code {

        /**
         * @Fields ok : 成功
         */
        public static final int success = 1;
        /**
         * @Fields error : 失败
         */
        public static final int error = 0;

    }

    public static final class Message {

        /**
         * @Fields ok : 成功
         */
        public static final String success = "成功!";
        /**
         * @Fields error : 失败
         */
        public static final String error = "系统错误!";

    }


    /**
     * excel类型
     */
    public static final class ExcelType {
        /**
         * xls
         */
        public static final String EXCEL_XLS = "xls";
        /**
         * xlsx
         */
        public static final String EXCEL_XLSX = "xlsx";
    }

    /**
     * 默认页码
     */
    public static final Integer PAGE_NUM = 1;

    /**
     * 默认页数
     */
    public static final Integer PAGE_SIZE = 10;
}
