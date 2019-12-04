package cn.mrmj.sms;

import cn.mrmj.response.Result;
import cn.mrmj.utils.RedisUtils;
import cn.mrmj.utils.StringUtils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * create by: mrmj
 * description: 阿里云短信发送控制层
 * create time: 2019/12/4 17:40
 */
@Component
public class SendMessager {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * create by: mrmj
     * description: 发送短信
     * phone:将要发送的短信手机号
     * type:模版idsendMessage
     * code:短信验证码长度
     * create time: 2019/12/4 17:42
     */
    public Result sendMessage(String phone, int type, String vcode)throws IOException, ClientException {
        //设置超时时间——可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient需要的几个参数
        // 短信API产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";
        // 短信API产品域名（接口地址固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";
        // 替换成你的AK
        final String accessKeyId = "LTAIt24RKs6FvxLh";
        // 你的accessKeySecret，参考本文档步骤2
        final String accessKeySecret = "6GpU0R6hwSgkUfDRjVGGAZfCKhJg9o";
        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        //String phone = hrequest.getParameter("phone");
//        String vcode = Utils.getRandomVcode(codeNum);
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("mrmj");
        // 必填:短信模板-可在短信控制台中找到
        String templateCode = null;
        //1：注册，2：换绑，3：找回密码
        if (1 == type){
            templateCode = "SMS_154950075";
        }else if (2 == type){
            templateCode = "SMS_154951823";
        }else {
            templateCode = "SMS_154960029";
        }

        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + vcode + "\"}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //EhcacheUtil.getInstance().put("myCache",phone,vcode);//用户的验证码
            //EhcacheUtil.getInstance().put("myCache","time"+phone,new Date().getTime());//用户的时间
//            this.setRedis(phone, vcode);
            redisUtils.set(phone,vcode);//用户的验证码
            //请求成功
            return Result.success(vcode);
        }else {
            return Result.fail(1,"发送失败");
        }
    }

    /**
     * create by: mrmj
     * description: 将手机号码和验证码存入Redis
     * create time: 2019/12/4 17:47
     */
    public void setRedis(String phone, String vcode){
        redisUtils.set(phone,vcode);
    }

    /**
     * create by: mrmj
     * description: 验证码校验
     * create time: 2019/12/4 17:47
     */
    public int checkSmsMsg(String telnum, String code){

        String vcode = redisUtils.get(telnum);
        if(StringUtils.isEmpty(vcode)){
            //验证码超时
            return 1;
        }
        if (!code.equals(vcode)){
            //验证码错误
            return 2;
        }
//        原本的验证完删除保存的验证码功能
        redisUtils.del(telnum);
        return 0;
    }

}
