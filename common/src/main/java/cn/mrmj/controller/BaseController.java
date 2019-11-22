package cn.mrmj.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * create by: mrmj
 * description: controller基类
 * create time: 2019/11/22 16:06
 */
@Controller
@CrossOrigin
public class BaseController {
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	@Resource
	protected HttpServletRequest req;
	@Resource
	protected HttpServletResponse resp;

	protected String getPara(String key) {
		String value = req.getParameter(key);
		return value != null ? value : null;
	}

	protected Byte getParaToByte(String key) {
		String value = req.getParameter(key);
		return value != null ? Byte.valueOf(value) : null;
	}

	protected Short getParaToShort(String key) {
		String value = req.getParameter(key);
		return value != null ? Short.valueOf(value) : null;
	}

	protected Integer getParaToInt(String key) {
		String value = req.getParameter(key);
		return value != null ? Integer.valueOf(value) : null;
	}

	protected Long getParaToLong(String key) {
		String value = req.getParameter(key);
		return value != null ? Long.valueOf(value) : null;
	}

	protected String getAttrToStr(String key) {
		Object value = req.getAttribute(key);
		return (String) value;
	}

	protected Byte getAttrToByte(String key) {
		Object value = req.getAttribute(key);
		return (Byte) value;
	}

	protected Short getAttrToShort(String key) {
		Object value = req.getAttribute(key);
		return (Short) value;
	}

	protected Integer getAttrToInt(String key) {
		Object value = req.getAttribute(key);
		return (Integer) value;
	}

	protected Long getAttrToLong(String key) {
		Object value = req.getAttribute(key);
		return (Long) value;
	}

	/**
	 * create by: mrmj
	 * description: 获取所有的方法放在map中
	 * create time: 2019/11/22 16:07
	 */
	protected Map<String, String> getAllParameter() {
		Map<String, String> map = new HashMap();
		Enumeration enums = req.getParameterNames();
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String paramValue = req.getParameter(paramName);
			//形成键值对应的map
			map.put(paramName, paramValue);
		}
		return map;
	}

}
