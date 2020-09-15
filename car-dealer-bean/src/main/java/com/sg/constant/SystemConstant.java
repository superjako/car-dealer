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
     * 请求设备类型
     */
    public static final class DeviceType {
        /**
         * ios请求
         */
        public static final String ios = "ios";
        /**
         * android请求
         */
        public static final String android = "android";
        /**
         * pc端请求
         */
        public static final String pc = "pc";
        /**
         * web端请求
         */
        public static final String wechat = "wechat";
    }

    /**
     * 给每个端发布的唯一标识
     */
    public static final class RequestKey {
        /**
         * ios请求
         */
        public static final String ios = "ZSASS1Q324343FD5FGGR4";
        /**
         * android请求
         */
        public static final String android = "AYEWMDSJ283294332";
        /**
         * pc端请求
         */
        public static final String pc = "7UJEW8EWKJIWQJSAIQI32K";
        /**
         * web端请求
         */
        public static final String wechat = "NHHJJ3EW3EWDSEDSIDSKE";
    }

    public static final class smsTemplate {

        public static final String verifyCode = "【清产核资系统】您的验证码是{0}，在{1}分钟内有效。如非本人操作请忽略本短信。";

        public static final String reportCode = "【清产核资系统】{0}提醒您，您的农村集体资产清产核资报表距离上报期限还有{1}天，请及时上报，以免延期！";

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
