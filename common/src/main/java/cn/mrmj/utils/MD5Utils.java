package cn.mrmj.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * create by: mrmj
 * description: md5加密
 * create time: 2019/11/21 14:35
 */
public class MD5Utils {
    public static String encoderByMd5(String str){
        //确定计算方法
        MessageDigest md5= null;
        String newstr = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //对字符串进行md5和base64双重加密
            newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /**
     * create by: mrmj
     * description: 判断用户密码是否正确
     * create time: 2019/11/21 14:43
     */
    public static boolean checkpassword(String newpasswd, String oldpasswd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if(encoderByMd5(newpasswd).equals(oldpasswd)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * create by: mrmj
     * description: 单纯的md5加密
     * create time: 2019/11/21 14:44
     */
    private static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * create by: mrmj
     * description: MD5加密所设置的静态变量 hexDigits
     * create time: 2019/11/21 14:47
     */
    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }


    public static void main(String[] args) {
        String pd = "888888";
        pd = MD5(pd);
        System.out.println("md5 == " + pd);
        pd = MD5Utils.encoderByMd5(pd);
        System.out.println("md5 + base64 == "+pd);

        System.out.println("双重加密 == " +MD5Utils.encoderByMd5(pd));
    }
}
