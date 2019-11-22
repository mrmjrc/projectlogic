package cn.mrmj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.mrmj.constant.RegexConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by: mrmj
 * description: 通用工具类
 * create time: 2019/11/22 14:37
 */
public class Utils {
    /**
     * create by: mrmj
     * description: 传入两个时间，获取分钟差
     * create time: 2019/11/22 14:42
     */
    public static long getMinuteResult(Date startTime, Date endTime) {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = (endTime.getTime() - startTime.getTime()) / 1000;//除以1000是为了转换成秒
        long min = between / 60;
        return min;
    }

    /**
     * create by: mrmj
     * description: Date时间 转 加上几个月
     * create time: 2019/11/22 14:52
     */
    public static Date addMonth(Date yourTime, Integer month) {
        Calendar c = Calendar.getInstance();
        c.setTime(yourTime);
        c.add(Calendar.MONTH, month);
        return c.getTime();
    }


    /**
     * create by: mrmj
     * description: json转对象
     * create time: 2019/11/22 14:52
     */
    public static <T> List<T> readListValue(String json, Class<T> tClazz) {
        return JSON.parseArray(json, tClazz);
    }


    /**
     * create by: mrmj
     * description: 获取随机数，length为产生的位数
     * create time: 2019/11/22 14:54
     */
    public static String getRandomString(int length) {
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        //长度为几就循环几次
        for (int i = 0; i < length; ++i) {
            //产生0-61的数字
            int number = random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    /**
     * create by: mrmj
     * description: 检查路径是否存在，不存在则创建路径
     * create time: 2019/11/22 14:55
     */
    public static void checkPath(String path) {
        String[] paths = null;
        if (path.contains("/")) {
            paths = path.split(File.separator);
        } else {
            paths = path.split(File.separator + File.separator);
        }
        if (null == paths || paths.length == 0) {
            return;
        }
        String pathdir = "";
        for (String string : paths) {
            pathdir = pathdir + string + File.separator;
            File file = new File(pathdir);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    /**
     * create by: mrmj
     * description: 获取当前时间
     * create time: 2019/11/22 15:18
     */
    public static Date getNowTime() {
        return new Date();
    }

    /**
     * create by: mrmj
     * description:  获取当前系统时间("yyyy-MM-dd")
     * create time: 2019/11/22 15:18
     */
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        return time;
    }

    /**
     * create by: mrmj
     * description:  比较两个时间的大小
     * create time: 2019/11/22 15:23
     */
    public static int compareDate(String date1, String date2) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sim.parse(date1);
            d2 = sim.parse(date2);
            if (d1.getTime() < d2.getTime()) {
                return 1;
            } else if (d1.getTime() > d2.getTime()) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * create by: mrmj
     * description: 截取两个日期之间的天数
     * create time: 2019/11/22 15:23
     */
    public static int getDays(Date beginDate, Date endDate) {
        //  SimpleDateFormat sim = new SimpleDateFormat( "yyyy/MM/dd");
        Date d1 = beginDate;
        Date d2 = endDate;
        int time = 0;
        try {
            time = (int) (d2.getTime() - d1.getTime()) / (3600 * 1000 * 24)+1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return time;
    }

    /**
     * create by: mrmj
     * description: 获取当前的时间戳
     * create time: 2019/11/22 15:24
     */
    public static Timestamp getTimestampNow() {
        return new Timestamp((new Date()).getTime());
    }

    /**
     * create by: mrmj
     * description: 比较两个时间戳是否一致
     * create time: 2019/11/22 15:24
     */
    public static Boolean CmpareTimestamp(Timestamp dbTs, Timestamp pgTs) {
        if (dbTs.getTime() == pgTs.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * create by: mrmj
     * description: 一个日期加上一个天数，得到一个新的日期字符串
     * create time: 2019/11/22 15:24
     */
    public static String getNewDateStr(String beginDate, long addDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = simpleDateFormat.parse(beginDate.trim());
        } catch (Exception e) {
            e.printStackTrace();
            return beginDate;
        }
        long time = d1.getTime();
        addDay = addDay * 24 * 60 * 60 * 1000;
        time += addDay;
        return simpleDateFormat.format(new Date(time));
    }

    /**
     * create by: mrmj
     * description: 判断date1和date2是否在同一周
     * create time: 2019/11/22 15:25
     */
    public static boolean isWeekSame(String date1, String date2) {
        date2 = getNewDateStr(date2.replace("/", "-"), -1).replace("-", "/");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * create by: mrmj
     * description: 判断list是否为空
     * create time: 2019/11/22 15:25
     */
    public static int hasNext(List<?> a) {
        if (a != null && a.size() > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * create by: mrmj
     * description: MD5加密
     * create time: 2019/11/22 15:26
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte b : md) {
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * create by: mrmj
     * description: 过滤字符串中的可能存在XSS攻击的非法字符
     * create time: 2019/11/22 15:27
     */
    public static String cleanXSS(String value) {
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    /**
     * create by: mrmj
     * description: 获取用户当前请求的IP地址
     * create time: 2019/11/22 15:27
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
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * create by: mrmj
     * description: 获取随机n位验证码
     * create time: 2019/11/22 15:28
     */
    public static String getRandomVcode(int n) {
        Random random = new Random();

        String result = "";
        for (int i = 0; i < n; i++) {
            result += random.nextInt(9) + 1;
        }
        return result;

    }



    /**
     * create by: mrmj
     * description: 判断验证码是否过期
     * create time: 2019/11/22 15:29
     */
    public static boolean timeoutvcode(Date vcodetime) throws Exception {
        boolean code = false;
        Date date = new Date();
        long i = date.getMinutes() - vcodetime.getMinutes();
        if (i > 30) {
            code = true;
        }
        return code;
    }

    /**
     * create by: mrmj
     * description: 逆向匹配接口，根据地址名称，匹配得到经纬度坐标
     * create time: 2019/11/22 15:30
     */
    public static Map<String, String> getPoint(String address) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            InputStream l_urlStream;
            java.net.URL l_url = new java.net.URL("http://api.map.baidu.com/geocoder/v2/?address=" + address.replaceAll(" ", "") + "&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation");
            java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();
            l_connection.connect();
            l_urlStream = l_connection.getInputStream();
            BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
            String str = l_reader.readLine();
            //用经度分割返回的网页代码  
            String s = "," + "\"" + "lat" + "\"" + ":";
            String strs[] = str.split(s, 2);
            String s1 = "\"" + "lng" + "\"" + ":";
            String a[] = strs[0].split(s1, 2);
            map.put("lin", a[1]);
            s1 = "}" + "," + "\"";
            String a1[] = strs[1].split(s1, 2);
            map.put("lat", a1[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * create by: mrmj
     * description:  计算百分率
     * create time: 2019/11/22 15:32
     */
    public static int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        p2 = 530 / w * 100;
        p = Math.round(p2);
        return p;
    }

    /**
     * create by: mrmj
     * description: 正则校验
     * create time: 2019/11/22 15:33
     */
    public static Boolean pattern(String param, String regEx) {
        // 要验证的字符串
        String str = param;
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }
    /**
     * create by: mrmj
     * description: 校验手机号
     * create time: 2019/11/22 15:33
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(RegexConstant.PHONE, mobile);
    }

    /**
     * create by: mrmj
     * description: 获取服务器IP地址
     * create time: 2019/11/22 15:34
     */
    public static String getServerIp() {
        String SERVER_IP = null;
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return SERVER_IP;
    }

    /**
     * create by: mrmj
     * description: 根据时间创建随机数
     * create time: 2019/11/22 15:35
     */
    public static String createOrdNum() {
        int shu2 =(int)(Math.random()*(9999-1000+1)+1000);
        String randId = StringUtil.getTimeFormat(new Date(),"yyyyMMddHHmmSSS" ) +shu2;
        return randId;
    }
}
