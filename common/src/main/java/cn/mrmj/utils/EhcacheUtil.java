package cn.mrmj.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

/**
 * create by: mrmj
 * description: Ehcache 缓存工具类，对 CacheManager 进行了封装
 * create time: 2019/11/12 18:52
 */
public class EhcacheUtil {

	private static final String path = "/ehcache.xml";

	private URL url;

	@Autowired
	private CacheManager manager;

	private static final EhcacheUtil ehCache  = new EhcacheUtil(path);

	private EhcacheUtil(String path) {
		url = getClass().getResource(path);
		manager = CacheManager.create(url);
	}

	//获取Ehcache的实例
	public static EhcacheUtil getInstance() {
//		if (ehCache == null) {
//			ehCache = new EhcacheUtil(path);
//		}
		return ehCache;
	}

	//添加
	public void put(String cacheName, String key, Object value) {
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	//通过name和key获取
	public Object get(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	//通过name获取
	public Cache get(String cacheName) {
		return manager.getCache(cacheName);
	}

	//通过name和key将缓存移除
	public void remove(String cacheName, String key) {
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}

}