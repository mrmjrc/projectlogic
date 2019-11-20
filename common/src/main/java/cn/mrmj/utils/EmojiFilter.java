package cn.mrmj.utils;


/**
 * create by: mrmj
 * description: 过滤emoji表情与非emoji表情
 * create time: 2019/11/12 18:57
 */

public class EmojiFilter {

    /**
     * create by: mrmj
     * description: 检测传入的字符串中是否有emoji字符
     * create time: 2019/11/12 18:58
     */
    public static boolean containsEmoji(String source) {
        if (source!=null && !"".equals(source)){
            int len = source.length();
            for (int i = 0; i < len; i++) {
                char codePoint = source.charAt(i);
                if (!notisEmojiCharacter(codePoint)) {
                    //判断确认有表情字符
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * create by: mrmj
     * description: 非emoji表情字符判断
     * create time: 2019/11/18 15:34
     */
    private static boolean notisEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * create by: mrmj
     * description: 过滤emoji 或者 其他非文字类型的字符
     * create time: 2019/11/18 15:35
     */
    public static String filterEmoji(String source) {
        if (!containsEmoji(source)) {
            //如果不包含，直接返回
            return source;
        }

        //该buf保存非emoji的字符
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (notisEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }

        if (buf == null) {
            //如果没有找到非emoji的字符，则返回无内容的字符串
            return "";
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

}
