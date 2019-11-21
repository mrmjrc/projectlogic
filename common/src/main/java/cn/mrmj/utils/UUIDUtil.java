package cn.mrmj.utils;

import java.util.UUID;

/**
 * create by: mrmj
 * description: 获取UUID的工具类
 * create time: 2019/11/21 18:22
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
