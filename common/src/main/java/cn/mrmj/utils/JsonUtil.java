package cn.mrmj.utils;

import com.alibaba.fastjson.JSON;

/**
 * create by: mrmj
 * description: 对json的封装？
 * create time: 2019/11/21 14:17
 */
public class JsonUtil {
    public static <T> T parseObject(String str, Class<T> clazz){
        // JSON.parseObject，将text解析为一个JSONObject对象并返回
        return JSON.parseObject(str,clazz);
    }
}
