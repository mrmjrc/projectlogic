package cn.mrmj.pay.weixin.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * create by: mrmj
 * description: 信任管理器(检查证书信息)
 * create time: 2019/12/4 14:16
 */
public class MyX509TrustManager implements X509TrustManager {

	// 检查客户端证书
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 检查服务器端证书
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 返回受信任的X509证书数组
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}