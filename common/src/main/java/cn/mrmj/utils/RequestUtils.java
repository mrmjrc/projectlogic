package cn.mrmj.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * create by: mrmj
 * description: Request 请求工具类
 * create time: 2019/11/12 14:04
 */
public class RequestUtils {

	/**
	 * create by: mrmj
	 * description: 获取Request请求流中的内容
	 * create time: 2019/11/12 14:04
	 */
	public static String getRequestContent(HttpServletRequest request) {
		//上传的字节流ServletInputStream
		ServletInputStream is = null;
		String strcont = null;
		try {
			is = request.getInputStream();
			StringBuilder content = new StringBuilder();
			byte[] b = new byte[1024];
			int lens = -1;
			while ((lens = is.read(b)) > 0) {
				content.append(new String(b, 0, lens));
			}
			strcont = content.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strcont;
	}

	/**
	 * create by: mrmj
	 * description: 通过Request请求获取客户端真实ip
	 * create time: 2019/11/12 14:08
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * create by: mrmj
	 * description: 根据 request 请求获得当前操作人id , name
	 * create time: 2019/11/12 14:12
	 */
	public static Integer getUserId(HttpServletRequest request) {
		Object o = request.getAttribute("_userId");
		return o != null ? (Integer) o : null;
	}

	public static String getUserName(HttpServletRequest request) {
		Object o = request.getAttribute("_userName");
		return o != null ? (String) o : null;
	}
}
