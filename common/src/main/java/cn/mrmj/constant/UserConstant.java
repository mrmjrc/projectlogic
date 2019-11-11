package cn.mrmj.constant;

/**
 * create by: mrmj
 * description: 用户相关的常量
 * create time: 2019/11/11 16:13
 */
public class UserConstant {
	//账号默认头像地址
	public static final String HEAD_IMAGE = "";

	//app_token 有效期 1周 7天
	public static final Long APP_TOKEN_PTTL = 7 * 24 * 60 * 60 * 1000L;

	//token 有效期 10年
	public static final Long CART_TOKEN_PTTL = 10 * 12 * 4 * 7 * 24 * 60 * 60 * 1000L;

	//后台管理token 有效期 两个小时
	public static final Long ADMIN_TOKEN_PTTL = 60 * 60 * 1000L;

	//人物类型  1: 学生，2：教师，3：家长
	public static final byte STUDENT_TYPE = 1;
	public static final byte TEACHER_TYPE = 2;
	public static final byte PATRIARCH_TYPE = 3;

}
