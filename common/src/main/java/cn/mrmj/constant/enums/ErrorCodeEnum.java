package cn.mrmj.constant.enums;

/**
 * create by: mrmj
 * description: api返回Result数据错误代码
 * create time: 2019/11/11 15:56
 * @author renchao
 */
public enum ErrorCodeEnum {
    OK(200, "success"),
    UNDEFINE_ERROR(1000, "未定义的错误"),
    NOT_AUTH_MENU(1001, "没有权限"),
    ONLY_LOGIN_ERROR(1002, "已在另一台设备登陆"),
    // token
    ACCESS_TOKEN_ERROR(2000, "登录过期或者无效"),
    ACCOUNT_ERROR(2001, "账号错误"),
    PWD_ERROR(2002, "密码错误"),
    TEL_ERROR(2003, "账号已被注册"),
    UPDATE_ERROR(2004, "操作失败"),
    PARAMETER_DONT_EXIST(2005, "参数不存在"),
    ACCOUNT_DONT_EXIST(2006, "用户不存在"),
    ACCOUNT_DONT_REG(2007, "用户未注册,请前往注册"),
    FILE_UPLOAD_ERROR(2008, "文件上传失败"),
    PARAMETER_ERROR(2009, "参数错误"),
    BALANCE_NOT_ENOUGH(2010, "余额不足"),
    OUT_OF_DEPOSITE(2011, "超出押金"),
    VERIFY_ERROR(2012, "验证码错误"),
    ROLE_DONT_EXIST(2013, "角色不存在"),
    ADMIN_ALREADY_EXIST(2014, "管理员已存在"),
    DATA_ERROR(2015, "数据错误"),
    DATA_DONT_EXIST(2016, "数据不存在"),
    NO_REPEAT_MODIFY(2019, "不能重复操作"),
    CANT_PAY(2021, "现在不能支付"),
    ORD_FROZEN(2022, "订单已被冻结"),
    WECHAT_LOGIN_CODE_ERROR(2023,"微信授权失败"),
    ACCOUNT_EXIST(2024, "用户已存在"),
    ;

    /**
     * 枚举的带参构造方法，传入代码和信息
     * @param code
     * @param message
     */
    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
