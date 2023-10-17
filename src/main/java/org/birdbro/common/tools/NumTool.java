package org.birdbro.common.tools;

/**
 * 生成数字
 *
 * @author birdbro
 * @date 11:01 2023-09-27
 **/
public class NumTool {


    /**
     * 十位数
     *
     * @author: birdbro
     * @since: 2023-09-27 11:07
     * @param:
     * @return:
     **/
    public static int getTensDigit(int pr, int num) {
        int digit = 10;
        String nCode = "0";
        if (num < digit) {
            nCode = pr + "0" + num;
        } else {
            nCode = pr + "" + num;
        }
        return Integer.parseInt(nCode);
    }


    /**
     * 十位数
     *
     * @author: birdbro
     * @since: 2023-09-27 11:07
     * @param:
     * @return:
     **/
    public static int getTensDigit(int pr, long num) {
        int digit = 10;
        String nCode = "0";
        if (num < digit) {
            nCode = pr + "0" + num;
        } else {
            nCode = pr + "" + num;
        }
        return Integer.parseInt(nCode);
    }


    /**
     * 十位数
     *
     * @author: birdbro
     * @since: 2023-09-27 11:07
     * @param:
     * @return:
     **/
    public static String getTensDigit(String pr, int num) {
        int digit = 10;
        String nCode = "0";
        if (num < digit) {
            nCode = pr + "0" + num;
        } else {
            nCode = pr + "" + num;
        }
        return nCode;
    }

}
