package cn.mrmj.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * create by: mrmj
 * description: 通过网页授权获取的用户信息，传输类
 * create time: 2019/12/9 14:13
 */
@Data
public class SNSUserInfoEntity implements Serializable {
	// 用户标识
	private String openId;
	// 用户昵称
	private String nickname;

	// 性别（1是男性，2是女性，0是未知）
	private int sex;
	// 国家
	private String country;
	// 省份
	private String province;
	// 城市
	private String city;
	// 用户头像链接
	private String headImgUrl;
	// 用户特权信息
	private List<String> privilegeList;
}
