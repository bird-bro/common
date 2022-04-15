package com.bird.common.util;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bird
 * @date 2022-4-7 15:52
 **/
public class CharsUtil {


    /**
     * 判断字符是否是中文
     * @author: bird
     * @date: 2022-4-7 15:55
     * @param:
     * @return:
     **/
    public static boolean isChinese(char c) {
        // 根据字节码判断
        return c >= 0x4E00 &&  c <= 0x9FA5;
    }

    /**
     * 获取首字符简拼
     * @author: bird
     * @date: 2022-4-7 15:55
     * @param:
     * @return:
     **/
    public static String oneChars(String str){
        if(StringUtils.isBlank(str)){
            return "";
        }
        String first = str.substring(0, 1);
        if(isChinese(first)){
            return PinyinUtil.getFirstLetter(first,"");
        }else {
            return first;
        }
    }


    /**
     * 判断字符串是否含有中文
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
                return true;}
        }
        return false;
    }


    /**
     * 正则匹配获取数据
     * @author: bird
     * @date: 2022-4-7 15:56
     * @param:
     * @return:
     **/
    public static String matcher(String soap,String rgex){
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }


}
