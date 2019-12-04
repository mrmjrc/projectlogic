package cn.mrmj.pay.weixin;


/**
 * create by: mrmj
 * description: 微信支付控制层，已废弃？
 * create time: 2019/12/4 14:23
 */
public class WeixinPayController {
//	// 商户相关资料
//	private static String appid = WeixinPayConstants.appid;
//	private static String appsecret = WeixinPayConstants.appsecret;
//	private static String partner = WeixinPayConstants.partner;
//	private static String partnerkey = WeixinPayConstants.partnerkey;
//
//	/**
//	 * 调取微信支付的页面
//	 * @param orderNum 订单编号
//	 * @param money 需要支付的钱
//	 * @param attach 附加数据
//	 * @param body 描述数据(购买的什么商品)
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public Result pay(String orderNum, String money, String attach, String body,
//					  HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
////		money="0.01";
//		JSONObject retMsgJson = new JSONObject();
//
//		// 金额转化为分为单位(必须输入)
//		float sessionmoney = Float.parseFloat(money);
//		String finalmoney = String.format("%.2f", sessionmoney);
//		finalmoney = finalmoney.replace(".", "");
//		int intMoney = Integer.parseInt(finalmoney);
//		String total_fee = String.valueOf(intMoney);// 总金额以分为单位，不带小数点
//
//		// 随机字符串(必须输入)
//		String currTime = TenpayUtil.getCurrTime();
//		String strTime = currTime.substring(8, currTime.length());// 8位日期
//		String strRandom = TenpayUtil.buildRandom(4) + "";// 四位随机数
//		String strReq = strTime + strRandom;// 10位序列号,可以自行调整。
//		String nonce_str = strReq;// 随机数
//
//		// 商户号(必须输入)
//		String mch_id = partner;
//
//		//String sub_mch_id="";// 子商户号 非必输
//		//String device_info = "";// 设备号 非必输
//
//		// 商户订单号(传入的订单号以及时间戳)(必须输入)
//		String out_trade_no = orderNum + "|" + System.currentTimeMillis()/1000l;
//
//		// 订单生成的机器 IP、终端IP(必须输入)
//		String spbill_create_ip = request.getRemoteAddr();
//
//		// 微信异步通知地址(必须输入)
//		String notify_url = WeixinPayConstants.notify_url;
//
//		// 交易类型(必须输入)
//		String trade_type = "APP";
//
////		String openid=openId;
////		String openid="oU6l30Rl1Z6kMZqHBFPw97d0aLXI";
////		String openid="o8I9nwQvxMu7C4zMC5yzMWVJeEkU";
////		System.out.println("openId====="+openId);
//
//		// 对以下字段进行签名
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", appid);                         //是(appid)
//		packageParams.put("mch_id", mch_id);                       //是(商品号)
//		packageParams.put("nonce_str", nonce_str);                 //是(随机字符串)
//		packageParams.put("attach", attach);                       //否
//		packageParams.put("body", body);                           //是(商品描述)
//		packageParams.put("notify_url", notify_url);               //是(通知地址)
//		packageParams.put("out_trade_no", out_trade_no);           //是(商户订单号)
//		packageParams.put("spbill_create_ip", spbill_create_ip);   //是(终端IP)
//		packageParams.put("total_fee", total_fee);                 //是(订单的总价)
//		packageParams.put("trade_type", trade_type);               //是(交易类型)(公众号支付)
////		packageParams.put("timestamp", timestamp);
////		packageParams.put("openid", openid);
//
//
//		RequestHandler reqHandler = new RequestHandler(request, response);
//		reqHandler.init(appid, appsecret, partnerkey);
//
//		// 获取签名
//		String sign = reqHandler.createSign(packageParams);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<attach>"
//				+ attach + "</attach>" + "<body><![CDATA[" + body
//				+ "]]></body>" + "<mch_id>" + mch_id + "</mch_id>"
//				+ "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>"
//				+ notify_url + "</notify_url>" + "<out_trade_no>"
//				+ out_trade_no + "</out_trade_no>" + "<spbill_create_ip>"
//				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>"
//				+ total_fee + "</total_fee>" + "<trade_type>" + trade_type
//				+ "</trade_type>" + "<sign>" + sign + "</sign>" +"</xml>";
//
//		String allParameters = "";
//		try {
//			allParameters = reqHandler.genPackage(packageParams);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//		String createOrderURL = WeixinPayConstants.createOrderURL;
//		String prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
//		System.out.println("prepay_id======"+prepay_id);
//		try {
//			if (prepay_id.equals("")) {
//				return Result.fail();
//			}
//		} catch (Exception e1) {
//
//			e1.printStackTrace();
//		}
//		// 获取到prepayid后对以下字段进行签名最终发送给app
//		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
//		String timestamp = Sha1Util.getTimeStamp();
//		finalpackage.put("appid", appid);        //公众号支付
//		finalpackage.put("noncestr", nonce_str); //随机字符串
//		finalpackage.put("package", "Sign=WXPay");
////		finalpackage.put("signType", "MD5");
//		finalpackage.put("partnerid", WeixinPayConstants.partner);
//		finalpackage.put("prepayid", prepay_id);
//		finalpackage.put("timestamp", timestamp);//时间戳
//		String finalsign = reqHandler.createSign(finalpackage);
//
////		parameters.put("appid", request.appId);
////		  parameters.put("noncestr", request.nonceStr);
////		  parameters.put("package", request.packageValue);
////		  parameters.put("partnerid", request.partnerId);
////		  parameters.put("prepayid", request.prepayId);
////		  parameters.put("timestamp", request.timeStamp);
////
//		retMsgJson.put("msg", "ok");
//		retMsgJson.put("appid", appid);
//		retMsgJson.put("timestamp", timestamp);
//		retMsgJson.put("noncestr", nonce_str);
//		retMsgJson.put("partnerid", WeixinPayConstants.partner);
//		retMsgJson.put("prepayid", prepay_id);
//		retMsgJson.put("package", "Sign=WXPay");
//		retMsgJson.put("sign", finalsign);
////		CommonUtils.returnCode(response, new ReturnData(0, "操作成功", retMsgJson));
//
//		return Result.success(retMsgJson);
//	}
//
////	@Autowired
////	private PayService payService;
//	// 微信异步通知
//	@RequestMapping(value = "/notify")
//	public void notify(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		InputStream in = request.getInputStream();
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		while ((len = in.read(buffer)) != -1) {
//			out.write(buffer, 0, len);
//		}
//		out.close();
//		in.close();
//		String msgxml = new String(out.toByteArray(), "utf-8");// xml数据
//		System.out.println(msgxml);
//		Map map = new GetWxOrderno().doXMLParse(msgxml);
//		String result_code = (String) map.get("result_code");
//		String err_code = (String) map.get("err_code");
//		String out_trade_no = (String) map.get("out_trade_no");
//		String total_fee = (String) map.get("total_fee");
//		String sign = (String) map.get("sign");
//		Double amount = new Double(total_fee) / 100;// 获取订单金额
//		String attach = (String) map.get("attach");
//		String sn = out_trade_no.split("\\|")[0];// 获取订单编号
//		Long userId = Long.valueOf(attach); //主播id
//		//Member member = memberService.find(memberid);
//		//Order order = orderService.findBySn(sn);
//		if (result_code.equals("SUCCESS")/* && member != null && order != null*/) {
//			// 验证签名
//			//float sessionmoney = Float.parseFloat(order.getAmount().toString());
//			//String finalmoney = String.format("%.2f", sessionmoney);
//			//finalmoney = finalmoney.replace(".", "");
//			//int intMoney = Integer.parseInt(finalmoney);
//			// 总金额以分为单位，不带小数点
//			//	String order_total_fee = String.valueOf(intMoney);
//			String fee_type = (String) map.get("fee_type");
//			String bank_type = (String) map.get("bank_type");
//			String cash_fee = (String) map.get("cash_fee");
//			String is_subscribe = (String) map.get("is_subscribe");
//			String nonce_str = (String) map.get("nonce_str");
//			String openid = (String) map.get("openid");
//			String return_code = (String) map.get("return_code");
//			String sub_mch_id = (String) map.get("sub_mch_id");
//			String time_end = (String) map.get("time_end");
//			String trade_type = (String) map.get("trade_type");
//			String transaction_id = (String) map.get("transaction_id");
//			// 需要对以下字段进行签名
//			SortedMap<String, String> packageParams = new TreeMap<String, String>();
//			packageParams.put("appid", appid);
//			packageParams.put("attach", attach); // 用自己系统的数据：会员id
//			packageParams.put("bank_type", bank_type);
//			packageParams.put("cash_fee", cash_fee);
//			packageParams.put("fee_type", fee_type);
//			packageParams.put("is_subscribe", is_subscribe);
//			packageParams.put("mch_id", partner);
//			packageParams.put("nonce_str", nonce_str);
//			packageParams.put("openid", openid);
//			packageParams.put("out_trade_no", out_trade_no);
//			packageParams.put("result_code", result_code);
//			packageParams.put("return_code", return_code);
//			packageParams.put("sub_mch_id", sub_mch_id);
//			packageParams.put("time_end", time_end);
//			packageParams.put("total_fee", total_fee); // 用自己系统的数据：订单金额
//			packageParams.put("trade_type", trade_type);
//			packageParams.put("transaction_id", transaction_id);
//			RequestHandler reqHandler = new RequestHandler(request, response);
//			reqHandler.init(appid, appsecret, partnerkey);
//			String endsign = reqHandler.createSign(packageParams);
//			if (sign.equals(endsign)) {// 验证签名是否正确
//											// 官方签名工具地址：https://pay.weixin.qq.com/wiki/tools/signverify/
//				try {
//					// 处理自己的业务逻辑
//
////					int code = payService.payReturn(sn, attach, amount+"", request, 1);
////					if (code == 0) {
////						response.getWriter().write(setXml("FAIL", "参数异常"));
////					}
//					response.getWriter().write(setXml("SUCCESS", "OK")); // 告诉微信已经收到通知了
//				} catch (Exception e) {
//					response.getWriter().write(setXml("FAIL", "系统异常"));
//					System.out.println("微信支付异步通知异常");
//				}
//			}else {
//				response.getWriter().write(setXml("FAIL", "通知签名验证失败")); // 告诉微信已经收到通知了
//			}
//		}else {
//			//logger.info("支付失败,错误信息：" + packageParams.get("err_code"));
//			response.getWriter().write(setXml("FAIL", err_code)); // 告诉微信已经收到通知了
//		}
//	}
//
//	public static String setXml(String return_code, String return_msg) {
//		return "<xml><return_code><![CDATA[" + return_code
//				+ "]]></return_code><return_msg><![CDATA[" + return_msg
//				+ "]]></return_msg></xml>";
//	}
}