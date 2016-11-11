package com.example.androidexp.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
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

    /**
     * 半角字符转化为全角字符
     *
     * @param input 要转化的字符
     * @return 转化后的结果
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 整数转字节数组
     *
     * @param i
     * @return
     */
    public static byte[] intToByte(int i) {
        byte[] bt = new byte[4];
        bt[0] = (byte) (0xff & i);
        bt[1] = (byte) ((0xff00 & i) >> 8);
        bt[2] = (byte) ((0xff0000 & i) >> 16);
        bt[3] = (byte) ((0xff000000 & i) >> 24);
        return bt;
    }

    /**
     * 字节数组转整数
     *
     * @param bytes
     * @return
     */
    public static int bytesToInt(byte[] bytes) {
        int num = bytes[0] & 0xFF;
        num |= ((bytes[1] << 8) & 0xFF00);
        num |= ((bytes[2] << 16) & 0xFF0000);
        num |= ((bytes[3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 判断是否全为数字
     *
     * @param content
     * @return
     */
    public static boolean isAllNumber(String content) {
        if (isNullOrEmpty(content)) {
            return false;
        }
        Pattern p = Pattern.compile("\\-*\\d+");
        Matcher m = p.matcher(content);
        return m.matches();
    }
}
