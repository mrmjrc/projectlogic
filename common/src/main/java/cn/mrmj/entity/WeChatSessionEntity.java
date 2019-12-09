package cn.mrmj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * create by: mrmj
 * description: 微信会话信息获取
 * create time: 2019/12/9 14:20
 */
@Data
public class WeChatSessionEntity implements Serializable {
    /**
     * 获取用户的唯一标识
     */
    private String openid;
    /**
     * 获取会话秘钥
     */
    private String session_key;
}
