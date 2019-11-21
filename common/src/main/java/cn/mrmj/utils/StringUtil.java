package cn.mrmj.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * create by: mrmj
 * description: 字符串工具类
 * create time: 2019/11/21 18:32
 */
public class StringUtil extends StringUtils {
    /**
     * create by: mrmj
     * description: 去除最后一个为,号的字符
     * create time: 2019/11/21 18:33
     */
    public static String delLastComma(String str){
        if(str != null){
            str = str.substring(0,str.length()-1);
        }
        return str;
    }

    /**
     * create by: mrmj
     * description: 去除前缀为0的字符
     * create time: 2019/11/21 18:33
     */
    public static String removePrefixZero(String str){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c != 48){
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
