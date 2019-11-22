package cn.mrmj.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
     * create by: mrmj
     * description: 判断String是否为空
     * create time: 2019/11/22 15:04
     */
    public static boolean isEmptyString(String value) {
        if (value == null || value.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * create by: mrmj
     * description: 只判断多个String是否为空(无论有没全角或半角的空格)
     *  若非空则返回true,否则返回false
     * create time: 2019/11/22 15:06
     */
    public static boolean isEmptyAllString(String... str) {
        if (null == str) {
            return true;
        }
        for (String s : str) {
            if (isEmptyString(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * create by: mrmj
     * description: 替换字符串
     * create time: 2019/11/22 15:32
     */
    public static String replaceIndex(int index, String res, String str) {
        return res.substring(0, index) + str + res.substring(index + 1);
    }


    /**
     * create by: mrmj
     * description: String类型转为int类型
     * create time: 2019/11/22 15:08
     */
    public static Integer parseInt(String string, Integer def) {
        if (isEmptyString(string)) {
            return def;
        }
        int num = def;
        try {
            num = Integer.parseInt(string);
        } catch (Exception e) {
            num = def;
        }
        return num;
    }

    /**
     * create by: mrmj
     * description: String类型转为short类型
     * create time: 2019/11/22 15:09
     */
    public static short parseShort(String string, short def) {
        if (isEmptyString(string)) {
            return def;
        }
        short num = def;
        try {
            num = Short.parseShort(string);
        } catch (Exception e) {
            num = def;
        }
        return num;
    }

    /**
     * create by: mrmj
     * description: String类型转为long类型
     * create time: 2019/11/22 15:09
     */
    public static Long parseLong(String string, Long def) {
        if (isEmptyString(string)) {
            return def;
        }
        long num;
        try {
            num = Long.parseLong(string);
        } catch (Exception e) {
            num = def;
        }
        return num;
    }

    /**
     * create by: mrmj
     * description: String类型转为double类型
     * create time: 2019/11/22 15:10
     */
    public static Double parseDouble(String string, Double def) {
        if (isEmptyString(string)) {
            return def;
        }
        double num;
        try {
            num = Double.parseDouble(string);
        } catch (Exception e) {
            num = def;
        }
        return num;
    }

    /**
     * create by: mrmj
     * description: String类型转为float类型
     * create time: 2019/11/22 15:11
     */
    public static Float parseFloat(String string, Float def) {
        if (isEmptyString(string)) {
            return def;
        }
        float num;
        try {
            num = Float.parseFloat(string);
        } catch (Exception e) {
            num = def;
        }
        return num;
    }

    /**
     * String -> float
     *
     * @param
     * @return long
     */
    public static float parseFloat(String string, Float def, int digit) {
        if (isEmptyString(string)) {
            return def;
        }
        float num;
        try {
            num = Float.parseFloat(string);
        } catch (Exception e) {
            num = def;
        }
        if (digit > 0 && num != def) {
            BigDecimal bigDecimal = new BigDecimal(num);
            float twoDecimalP = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            return twoDecimalP;
        } else {
            return num;
        }
    }

    /**
     * create by: mrmj
     * description: 时间转字符串(Date------String),根据指定的格式转化，
     * 没有的话按照默认的格式转换
     * create time: 2019/11/22 15:13
     */
    public static String getTimeFormat(Date date, String format) {
        SimpleDateFormat sdFormat;
        if (isEmptyString(format)) {
            sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            sdFormat = new SimpleDateFormat(format);
        }
        try {
            return sdFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * create by: mrmj
     * description: 判断集合中是否存在指定的字符串，true：存在，false：不存在
     * create time: 2019/11/22 15:16
     */
    public static boolean exists(List<String> list, String key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(key)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * create by: mrmj
     * description: 格式化时间 String转换Date "yyyy-MM-dd HH:mm:ss"
     * create time: 2019/11/22 15:17
     */
    public static Date getDateFormat(String date, String format) {
        if (isEmptyString(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * create by: mrmj
     * description: 获取当前系统时间(返回Time)时间戳
     * create time: 2019/11/22 15:22
     */
    public static Long getNowDate() {
        return (getDateFormat(getTimeFormat(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd")).getTime();
    }





}
