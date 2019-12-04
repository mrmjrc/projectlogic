package cn.mrmj.push;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import cn.mrmj.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * create by: mrmj
 * description: 极光工具类
 * create time: 2019/12/4 14:25
 */
public class JGUtils {

	// 在极光注册上传应用的 appKey 和 masterSecret，必填
	private static final String appKey = "8a1aebc3317cecc0821e9a13";

	// 必填，每个应用都对应一个masterSecret
	private static final String masterSecret = "1a82debcb14aea47b16376c4";

	//private final static Logger logger = LoggerFactory.getLogger(JGUtils.class);

	private static JPushClient jpush = null;

	public static void main(String[] args) {

		String message = "呵呵呵";
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "账户在其他设备上登录了!");
		map.put("action", "logOut");

		// 测试发送消息或者通知
		jPushSend(null,message,null,"160a3797c844a1f1e70");
	}

	/**
	 * create by: mrmj
	 * description: 推送消息
	 * message : 自定义消息推送内容, notification ：通知推送内容, map ：额外字段, regId ：推送设备id,多个设备用逗号拼接
	 * create time: 2019/12/4 14:26
	 */
	public static Integer jPushSend(String message, String notification, Map<String, String> map, String regId) {

		//logger.info("enter jPushSend.");
		jpush = new JPushClient(masterSecret, appKey);

		PushPayload payload = buildPushObject_all_all_alert_message(message,notification,map,regId);

		PushResult msgResult = null;
		try {
			msgResult = jpush.sendPush(payload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}

		if (null != msgResult) {
			//System.out.println(msgResult.getResponseCode());
			return msgResult.getResponseCode();
		} else {
			//System.out.println("无法获取数据");
			return null;
		}
	}

	/**
	 * create by: mrmj
	 * description: setPlatform用于设置平台，setAudience用于设置推送的人，setMessage表示推送的是自定义信息，推送通知可以设置PushPayload.alertAll("test");
	 * create time: 2019/12/4 14:27
	 */
	private static PushPayload buildPushObject_all_all_alert_message(
            String message, String notification, Map<String, String> map, String alias) {
		if (alias == null) {
			if (StringUtil.isNotEmpty(message)) {
				return PushPayload
						.newBuilder()
						.setPlatform(Platform.all())
						.setAudience(Audience.all())
						.setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build())
						.build();
			}else {
				return PushPayload
						.newBuilder()
						.setPlatform(Platform.all())
						.setAudience(Audience.all())
						.setNotification(Notification.alert(notification))
						.build();
			}
		}else {
			if (StringUtil.isNotEmpty(message)) {
				return PushPayload
						.newBuilder()
						.setPlatform(Platform.all())
						.setAudience(Audience.registrationId(alias))
						.setMessage(Message.newBuilder().setMsgContent(message).addExtras(map).build())
						.build();
			}else {
				return PushPayload
						.newBuilder()
						.setPlatform(Platform.all())
						.setAudience(Audience.registrationId(alias))
						.setNotification(Notification.alert(notification))
						.build();
			}
		}


		// return PushPayload.newBuilder().setPlatform(Platform.all())
		// .setAudience(Audience.alias("1"))
		// .setNotification(Notification.alert("邀请用户已注册，积分增加50")).build();
	}
}
