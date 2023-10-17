package org.birdbro.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 *
 * @author birdbro
 * @date 9:39 2022-12-7
 **/
public class CharsTool {

    /**
     * 判断字符是否是中文
     *
     * @author: bird
     * @date: 2022-4-7 15:55
     * @param:
     * @return:
     **/
    public static boolean isChinese(char c) {
        // 根据字节码判断
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    /**
     * 获取首字符简拼
     *
     * @author: bird
     * @date: 2022-4-7 15:55
     * @param:
     * @return:
     **/
//    public static String oneChars(String str) {
//        if (StringUtils.isBlank(str)) {
//            return "";
//        }
//        String first = str.substring(0, 1);
//        if (isChinese(first)) {
//            return PinyinUtil.getFirstLetter(first, "");
//        } else {
//            return first;
//        }
//    }


    /**
     * 判断字符串是否含有中文
     *
     * @author: bird
     * @date: 2022-4-7 15:55
     * @param:
     * @return:
     **/
    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                // 有一个中文字符就返回
                return true;
            }
        }
        return false;
    }


    /**
     * @author: birdbro
     * @date: 2022-4-22 13:25
     * @param:
     * @return:
     **/
    public static String matcher(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        return m.find() ? m.group(1) : "";
    }


}
