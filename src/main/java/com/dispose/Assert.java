package com.dispose;

import com.dispose.exception.details.BusinessErrorCode;
import com.dispose.exception.category.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @date 12:31 2020/6/26
 * @author wangpeng
 */
@Slf4j
public class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }
    public static void isBlank(String str, String message,String code) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message,code,"");
        }
    }
    public static void isBlank(String str, String message, String code, String info) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message,code,info);
        }
    }
    public static void isBlank(String str, String message, BusinessErrorCode errorCode) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message,errorCode);
        }
    }
    public static void isBlank(String str, BusinessErrorCode errorCode) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(errorCode);
        }
    }


    public static void isAnyBlank(String message, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message);
        }
    }
    public static void isNull(Object object, String message, String code) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,code,"");
        }
    }
    public static void isNull(Object object, String message, String code, String info) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,code,info);
        }
    }
    public static void isNull(Object object, String message, BusinessErrorCode errorCode) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,errorCode);
        }
    }
    public static void isNull(Object object, BusinessErrorCode errorCode) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode);
        }
    }


    public static void isNotNull(Object object, String message) {
        if (!ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message);
        }
    }
    public static void isNotNull(Object object, String message,String code) {
        if (!ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,code,"");
        }
    }
    public static void isNotNull(Object object, String message,String code, String info) {
        if (!ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,code,info);
        }
    }
    public static void isNotNull(Object object, String message,BusinessErrorCode errorCode) {
        if (!ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,errorCode);
        }
    }
    public static void isNotNull(Object object, BusinessErrorCode errorCode) {
        if (!ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode);
        }
    }


    public static void isListOnlyOne(List<?> list, String message) {
        if(ObjectUtils.isEmpty(list) || list.size()>1){
            throw new BusinessException(message);
        }
    }
    public static void isListOnlyOne(List<?> list, String message, String code) {
        if(ObjectUtils.isEmpty(list) || list.size()>1){
            throw new BusinessException(message,code);
        }
    }
    public static void isListOnlyOne(List<?> list, String message, String code, String info) {
        if(ObjectUtils.isEmpty(list) || list.size()>1){
            throw new BusinessException(message,code,info);
        }
    }
    public static void isListOnlyOne(List<?> list, String message, BusinessErrorCode errorCode) {
        if(ObjectUtils.isEmpty(list) || list.size()>1){
            throw new BusinessException(message,errorCode);
        }
    }
    public static void isListOnlyOne(List<?> list, BusinessErrorCode errorCode) {
        if(ObjectUtils.isEmpty(list) || list.size()>1){
            throw new BusinessException(errorCode);
        }
    }



    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null) {return true;}

        if (obj instanceof CharSequence) {return ((CharSequence) obj).length() == 0;}

        if (obj instanceof Collection) {return ((Collection) obj).isEmpty();}

        if (obj instanceof Map) {return ((Map) obj).isEmpty();}

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




    public static void validated(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()){
                throw new BusinessException(
                        String.format("参数：{%s}校验失败！原因：{%s}",error.getField(),error.getCode()),
                        "A0001",
                        error.getDefaultMessage());
            }
        }
    }
}
