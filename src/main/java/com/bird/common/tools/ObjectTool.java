package com.bird.common.tools;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 对象操作辅助
 * @author bird
 * @date 2021-7-21 11:07
 **/
public class ObjectTool {

    /**
     * 复制对象属性
     * @author:bird
     * @date: 2021-7-21 11:09
     * @param: source 源  target 目标
     * @return:
     **/
    public static void copyProperties(Object source,Object target) {
        Assert.isNull(source, "源对象不能为空");
        Assert.isNull(target, "目标对象不能为空");
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制对象属性，忽略空字段
     * @author:bird
     * @date: 2021-7-21 11:08
     * @param: source 源  target 目标
     * @return:
     **/
    public static void copyPropertiesIgnoreNull(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取空属性
     * @author:bird
     * @date: 2021-7-21 11:09
     * @param:
     * @return:
     **/
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(ObjectUtils.isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }




}
