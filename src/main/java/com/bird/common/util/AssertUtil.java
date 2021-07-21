package com.bird.common.util;

import java.util.*;

/**
 * 对象校验
 * @author bird
 * @date 2021-7-21 10:00
 **/
public class AssertUtil {

    /**
     *判断数组是否为空
     * @Params: [obj]
     * @Return: boolean
     * @Author: wangpeng
     * @Time: 2020/6/23 14:51
     */
    public static <T> boolean isEmpty(T[] obj){
        return null == obj || 0==obj.length;
    }

    /**
     *判断数组是否不为空
     * @Params: [obj]
     * @Return: boolean
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static <T> boolean isNotEmpty(T[] obj) {
        return !isEmpty(obj);
    }

    /**
     *判断对象是否为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    /**
     *判断对象是否不为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     *判断字符串是否为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     *判断字符串是否不为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     *判断集合是否为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isEmpty(Collection obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     *判断集合是否不为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isNotEmpty(Collection obj) {
        return !isEmpty(obj);
    }

    /**
     *判断map是否为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isEmpty(Map obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 判断map是否不为空
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isNotEmpty(Map obj) {
        return !isEmpty(obj);
    }


    /**
     * char数值是否是数字
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean charIsNumb(int charValue) {
        return charValue >= 48 && charValue <= 57 || charValue >= 96 && charValue <= 105;
    }


    /**
     * 判断字符串是否是纯数字浮点类型
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isFloat(String s) {
        if(!(s.indexOf(".") > -1)){
            return false;
        }
        char[] chars = s.toCharArray();
        boolean flag = true;
        for (char aChar : chars) {
            if(aChar != 46){
                if(!(aChar >= 48 && aChar <= 57 || aChar >= 96 && aChar <= 105)){
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }


    /**
     * 非纯数字浮点类型
     * @author:bird
     * @date: 2021-7-21 10:24
     * @param:
     * @return:
     **/
    public static boolean isNotFloat(String s) {
        return !isFloat(s);
    }

 /**
  * 字符串是否是数字
  * @author:bird
  * @date: 2021-7-21 10:25
  * @param:
  * @return:
  **/
    public static boolean isNumb(String str) {
        if (isEmpty((Object)str)) {
            return false;
        } else {
            char[] chr = str.toCharArray();

            for(int i = 0; i < chr.length; ++i) {
                if (chr[i] < '0' || chr[i] > '9') {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 字符串是否不是是数字
     * @author:bird
     * @date: 2021-7-21 10:25
     * @param:
     * @return:
     **/
    public static boolean isNotNumb(String str) {
        return !isNumb(str);
    }


    /**
     * 判断字符串是否有长度,并自定义异常信息
     * @author:bird
     * @date: 2021-7-21 10:25
     * @param:
     * @return:
     **/
    public static void hasLength(String str, String msg) {
        if (str == null || str.length() < 1) {
            throw new RuntimeException(msg);
        }
    }


    /**
     * 自定义参数校验异常
     * @author:bird
     * @date: 2021-7-21 10:25
     * @param:
     * @return:
     **/
    public static void paramCheck(String msg,Object...obj) {
        for (Object o : obj) {
            // 参数异常
            if(isEmpty(o)){
                throw new RuntimeException(msg);
            }
        }
    }

    /**
     * 可变参数,判断是否所有对象都为空
     * @author:bird
     * @date: 2021-7-21 10:26
     * @param:
     * @return:
     **/
    public static boolean isAllEmpty(Object... obj) {
        Object[] var1 = obj;
        int var2 = obj.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 可变参数-判断只要有任意一个对象为空,则为true
     * @author:bird
     * @date: 2021-7-21 10:26
     * @param:
     * @return:
     **/
    public static boolean isAnyEmpty(Object... obj) {
        Object[] var1 = obj;
        int var2 = obj.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 可变参数 -判断是否所有参数都不为空
     * @Params:
     * @Return:
     * @Time: 2020/6/23 14:53
     */
    public static boolean isAllNotEmpty(Object... obj) {
        Object[] var1 = obj;
        int var2 = obj.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个对象是否相等
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isEqual(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        } else if (o2 == null) {
            return false;
        } else if (o1.getClass().isArray()) {
            for(int i = 0; i < ((Object[])((Object[])o1)).length; ++i) {
                if (!isEqual(((Object[])((Object[])o1))[i], ((Object[])((Object[])o2))[i])) {
                    return false;
                }
            }

            return true;
        } else if (Collection.class.isAssignableFrom(o1.getClass())) {
            Iterator i1 = ((Collection)o1).iterator();
            Iterator i2 = ((Collection)o2).iterator();
            if (((Collection)o1).size() != ((Collection)o2).size()) {
                return false;
            } else {
                for(int i = 0; i < ((Collection)o1).size(); ++i) {
                    if (!isEqual(i1.next(), i2.next())) {
                        return false;
                    }
                }

                return true;
            }
        } else if (!Map.class.isAssignableFrom(o1.getClass())) {
            return o1.equals(o2);
        } else {
            Map<Object, Object> m1 = (Map)o1;
            Map<Object, Object> m2 = (Map)o2;
            if (m1.size() != m2.size()) {
                return false;
            } else if (!isEqual(m1.keySet(), m2.keySet())) {
                return false;
            } else {
                Iterator var4 = m1.entrySet().iterator();

                Map.Entry o;
                do {
                    if (!var4.hasNext()) {
                        return true;
                    }

                    o = (Map.Entry)var4.next();
                } while(m2.containsKey(o.getKey()) && isEqual(o.getValue(), m2.get(o.getKey())));

                return false;
            }
        }
    }


    /**
     * 判断两个对象是否不相等
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean isNotEqual(Object o1, Object o2) {
        return !isEqual(o1,o2);
    }

    /**
     * 比较两个集合是否相等
     * @Params:
     * @Return:
     * @Author: wangpeng
     * @Time: 2020/6/23 14:53
     */
    public static boolean compare(List<Comparable> l1, List<Comparable> l2) {
        if (l1 != null && !l1.isEmpty()) {
            if (l2 != null && !l2.isEmpty()) {
                Collections.sort(l1);
                Collections.sort(l2);
                if (l1.size() != l2.size()) {
                    return false;
                } else {
                    for(int i = 0; i < l1.size(); ++i) {
                        if (((Comparable)l1.get(i)).compareTo(l2.get(i)) != 0) {
                            return false;
                        }
                    }

                    return true;
                }
            } else {
                return false;
            }
        } else {
            return l2 == null || l2.isEmpty();
        }
    }


    /*************************
     * Ver_1.0.1
     *************************/

    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null) {return true;}

        if (obj instanceof CharSequence) {return ((CharSequence) obj).length() == 0;}

        if (obj instanceof Collection) {return ((Collection) obj).isEmpty();}

        if (obj instanceof Map ) {return ((Map) obj).isEmpty();}

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }

}
