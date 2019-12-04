package cn.mrmj.sms;
import cn.mrmj.response.Result;
import cn.mrmj.utils.EhcacheUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * create by: mrmj
 * description: 发送邮件
 * create time: 2019/12/4 17:28
 */
public class SendMail {
    private final static String accessKey = "LTAIt24RKs6FvxLh";
    private final static String accessSecret = "6GpU0R6hwSgkUfDRjVGGAZfCKhJg9o";
    private static String temp = "您好，您的验证码为value,有效期为5分钟,如果不是本人操作请忽略。";
    private static String identyRemind = "您好，您的验证信息value";
    private static String regSubject = "【xx】注册通知邮件";
    private static String identSubject = "【xx】身份验证邮件";
    private static String changeSubject = "【xx】邮箱更换邮件";
    private static String passResetSubject = "【xx】重置密码邮件";

    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    //或"80"
    private static final String ALIDM_SMTP_PORT = "25";
    public static Result sample(String toAddress, String code, Integer type) {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKey, accessSecret);
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
//        try {
//        DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
//        } catch (ClientException e) {
//        e.printStackTrace();
//        }
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            // 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
//            request.setVersion("2017-06-22");
            request.setAccountName("xxxx@www.hzwodai.com");
            request.setFromAlias("xxx");
            request.setAddressType(0);
            request.setTagName("xxxx");
            request.setReplyToAddress(false);
            request.setToAddress(toAddress);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            if (type == 1){
                request.setSubject(regSubject);
            }else if(type == 2){
                request.setSubject(changeSubject);
            }else if(type == 3){
                request.setSubject(identSubject);
                request.setTextBody(identyRemind.replaceAll("value",code));
                SingleSendMailResponse httpResponse = client.getAcsResponse(request);
                //请求成功
                return Result.success();
            }else {
                request.setSubject(passResetSubject);
            }
            request.setTextBody(temp.replaceAll("value",code));
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
//           System.out.println(httpResponse);
            //用户的验证码
            EhcacheUtil.getInstance().put("myCache",toAddress,code);
            //用户的时间
            EhcacheUtil.getInstance().put("myCache","time"+toAddress,new Date().getTime());
            //请求成功
            return Result.success();
        } catch (ServerException e) {
            e.printStackTrace();
            return Result.fail(1,"发送失败");
        }
        catch (ClientException e) {
            e.printStackTrace();
            return Result.fail(1,"发送失败");
        }
    }

    private void smtpSend() throws UnsupportedEncodingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user", "发信地址");
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", "***");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession){
            //@Override
            //protected void updateMessageID() throws MessagingException {
            //设置自定义Message-ID值
            //setHeader("Message-ID", messageIDValue);
            //}
        };
        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress("发信地址", "发件人名称（用户自定义填写）");
            message.setFrom(from);
            //可选。设置回信地址
            Address[] a = new Address[1];
            a[0] = new InternetAddress("***");
            message.setReplyTo(a);
            // 设置收件人邮件地址，比如yyy@yyy.com
            InternetAddress to = new InternetAddress("收件人邮件地址");
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
            //InternetAddress[] adds = new InternetAddress[2];
            //adds[0] = new InternetAddress("xxxxx@qq.com");
            //adds[1] = new InternetAddress("xxxxx@qq.com");
            //message.setRecipients(Message.RecipientType.TO, adds);
            String ccUser = "抄送邮箱";
            // 设置多个抄送地址
            if(null != ccUser && !ccUser.isEmpty()){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressCC;
                internetAddressCC = new InternetAddress().parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            String bccUser = "密送邮箱";
            // 设置多个密送地址
            if(null != bccUser && !bccUser.isEmpty()){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressBCC;
                internetAddressBCC = new InternetAddress().parse(bccUser);
                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
            }
            // 设置邮件标题
            message.setSubject("测试邮件");
            // 设置邮件的内容体
            message.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
            //若需要开启邮件跟踪服务，请使用以下代码设置跟踪链接头。首先域名需要备案，设置且已正确解析了CNAME配置；其次发信需要打Tag，此Tag在控制台已创建并存在，Tag创建10分钟后方可使用；
            //String tagName = "Test";
            //HashMap<String, String> trace = new HashMap<>();
            //trace.put("OpenTrace", "1");
            //trace.put("TagName", tagName);
            //String jsonTrace = JSON.toJSONString(trace);
            //String base64Trace = new String(Base64.encodeBase64(jsonTrace.getBytes()));
            //设置跟踪链接头
            //message.addHeader("X-AliDM-Trace", base64Trace);
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
    }

    /**
     *@描述 验证码校验
     *@参数
     *@返回值
     *@创建人  shiya
     *@创建时间  2018/6/9
     *@修改人和其它信息
     */
    public static int checkSmsMsg(String mail, String code){
        EhcacheUtil ehcacheUtil = EhcacheUtil.getInstance();
        Long timeLong = (Long)ehcacheUtil.get("myCache", "time" + mail);
        String vcode = (String) ehcacheUtil.get("myCache", mail);
        if (new Date().getTime() - timeLong > 5*60*1000){
            //验证码超时
            return 1;
        }
        if (!code.equals(vcode)){
            //验证码错误
            return 2;
        }
        ehcacheUtil.remove("myCache", "time" + mail);
        ehcacheUtil.remove("myCache", mail);
        return 0;
    }
    public static void main(String[] args) {
//        sample("gongshiya127@163.com","123456",1);
        sample("@163.com","您好，您的身份验证信息已经通过",3);
    }

}
