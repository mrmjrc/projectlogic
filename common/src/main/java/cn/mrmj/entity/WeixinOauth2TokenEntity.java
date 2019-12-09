package cn.mrmj.entity;

import lombok.Data;

/**
 * create by: mrmj
 * description: 微信获取网页授权信息
 * create time: 2019/12/9 14:17
 */
@Data
public class WeixinOauth2TokenEntity {
	// 网页授权接口调用凭证
	private String accessToken;
	// 凭证有效时长
	private int expiresIn;
	// 用于刷新凭证
	private String refreshToken;
	// 用户标识
	private String openId;
	// 用户授权作用域
	private String scope;

}
