package cn.mrmj.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.Map;

/**
 * create by: mrmj
 * description: 支付宝支付controller
 * create time: 2019/12/4 14:09
 */
@Component
public class Alipay {
//	@Autowired
	@Value("${pay.appid}")
	public String appid;

	@Value("${pay.private_key}")
	public String private_key;

	@Value("${pay.input_charset}")
	public String input_charset;

	@Value("${pay.ali_public_key}")
	public String ali_public_key;

	@Value("${pay.apiPayReturn}")
	public String apiPayReturn;

	@Value("${pay.sign_type}")
	public String sign_type;

	Logger logger = Logger.getLogger(Alipay.class);

	public String aliPay(String orderNum, String totalAmout, String params,
                         String subject) throws AlipayApiException {
		// 实例化客户端
		com.alipay.api.AlipayClient alipayClient = new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do", appid,
				private_key, "json", input_charset,
				ali_public_key, "RSA2");
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("支付"); // 商品的描述信息
		model.setSubject(subject); // 商品的标题
		model.setOutTradeNo(orderNum); // 订单号
		model.setTimeoutExpress("30m");
		model.setTotalAmount(totalAmout);
		model.setProductCode("QUICK_MSECURITY_PAY");
		model.setPassbackParams(params);///附加数据
		request.setBizModel(model);
		request.setNotifyUrl(apiPayReturn);
		// 这里和普通的接口调用不同，使用的是sdkExecute
		AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
		System.out.println(response.getBody());// 就是orderString
												// 可以直接给客户端请求，无需再做处理。
		return response.getBody();
	}

	/**
	 * 阿里回调函数
	 * 
	 * @param
	 */

//	@RequestMapping("aliPayReturn")
	public void aliPay_notify(HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
            AlipayApiException {
		// System.out.println("支付宝支付结果通知" + requestParams.toString());
		java.util.Map<String, String> params = new HashMap<String, String>();
		Map<?, ?> requestParams = request.getParameterMap();

		// 获取支付宝POST过来反馈信息)
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		// 验证签名
		// logger.info("支付宝支付结果通知参数" + params.toString());
		boolean flag = AlipaySignature.rsaCheckV1(params,
				ali_public_key, input_charset,
				sign_type);
		logger.info("签名验证结果===" + flag);
		if (flag) {
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				// 付款金额
				String amount = params.get("buyer_pay_amount");
				// 商户订单号
				String out_trade_no = params.get("out_trade_no");
				// 支付宝交易号
				String trade_no = params.get("trade_no");
				// 附加数据
				String passback_params = URLDecoder.decode(params
						.get("passback_params"));

				//回调业务
//				int payReturn = payService.payReturn(out_trade_no,
//						passback_params, amount, request, 2);
//				if (payReturn == 1) {
//					response.getWriter().print("success");
//					logger.info("success");
//				} else {
//					response.getWriter().print("failure");
//					logger.info("failure");
//				}
			}
		} else {
			response.getWriter().print("failure");
			logger.info("failure签名失败");
		}
	}
}
