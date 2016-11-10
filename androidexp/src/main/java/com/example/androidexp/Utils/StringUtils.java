package com.example.androidexp.Utils;

/**
 * Created by POST on 2016/11/10.
 */
public class StringUtils {

    /**
     * 判断给定字符串是否空白串。
     * 空白串是指由空格、制表符、回车符、换行符组成的字符串
     * 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input) || "null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为空
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || "".equals(text.trim()) || text.trim().length() == 0 || "null".equals(text.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证字符串内容是否合法
     *
     * @param content
     *            字符串内容
     * @return
     */
    public static boolean isLegalString(String content) {
        String illegal = "`~!#%^&*=+\\|{};:'\",<>/?○●★☆☉♀♂※¤╬の〆";
        boolean legal = true;
        L1: for (int i = 0; i < content.length(); i++) {
            for (int j = 0; j < illegal.length(); j++) {
                if (content.charAt(i) == illegal.charAt(j)) {
                    legal = false;
                    break L1;
                }
            }
        }
        return legal;
    }

    /**
     * 获取截取后的字符串
     *
     * @param text
     *            原字符串
     * @param length
     *            截取长度
     * @return
     */
    public static String getSubString(String text, int length) {
        return getSubString(text, length, true);
    }

    /**
     * 获取截取后的字符串
     *
     * @param text
     *            原字符串
     * @param length
     *            截取长度
     * @param isOmit
     *            是否加上省略号
     * @return
     */
    public static String getSubString(String text, int length, boolean isOmit) {
        if (isNullOrEmpty(text)) {
            return "";
        }
        if (getCharCount(text) <= length + 1) {
            return text;
        }

        StringBuffer sb = new StringBuffer();
        String Reg = "^[\u4e00-\u9fa5]{1}$";
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            String b = Character.toString(text.charAt(i));
            if (b.matches(Reg)) {
                result += 2;
            } else {
                result++;
            }

            if (result <= length + 1) {
                sb.append(b);
            } else {
                if (isOmit) {
                    sb.append("...");
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 得到字符串长度
     *
     * @param text
     * @return
     */
    public static int getCharCount(String text) {
        String Reg = "^[\u4e00-\u9fa5]{1}$";
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            String b = Character.toString(text.charAt(i));
            if (b.matches(Reg))
                result += 2;
            else
                result++;
        }
        return result;
    }
}
