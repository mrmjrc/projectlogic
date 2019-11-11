package cn.mrmj.constant;

/**
 * create by: mrmj
 * description: 开发中的返回信息常量定义,返回的数字定义一半都是Byte类型定义的
 * create time: 2019/11/11 16:04
 */
public class DevelopConstants {

    //系统退出提示信息
    public static final String LOGOUT_REMIND = "退出成功";


    //用户自身信息验证信息是否通过（1未提交，2审核中，3已通过，4未通过）
    public static final Byte ACC_USER_IS_PASS_NOT_SUBMIT = 1;
    public static final Byte ACC_USER_IS_PASS_SUBMIT = 2;
    public static final Byte ACC_USER_IS_PASS = 3;
    public static final Byte ACC_USER_IS_NOT_PASS = 4;

    //注册方式（1手机，2邮箱，3微信）
    public static final Byte ACC_USER_REGIST_WAY_PHONE = 1;
    public static final Byte ACC_USER_REGIST_WAY_EMAIL = 2;
    public static final Byte ACC_USER_REGIST_WAY_WEIXIN = 3;

    //APP端用户默认密码 123456 ，md5加密
    public static final String ACC_USER_DEFAULT_PASS = "4QrcOUm6Wau+VuBX8g+IPg==";

    //文件上传至阿里云后的返回路径是否直接储存至数据库，1是,2否
    public static final Byte FILE_SAVE_ALI = 1;
    public static final Byte FILE_NOT_SAVE_ALI = 2;
}
