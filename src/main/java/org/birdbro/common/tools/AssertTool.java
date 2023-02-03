package org.birdbro.common.tools;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import org.birdbro.common.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.birdbro.common.exception.BusinessException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collection;

/**
 * @author bird
 * @date 2021-7-21 9:58
 **/
@Slf4j
public class AssertTool {


    public static void isEmpty(Object object, String message) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message);
        }
    }
    public static void isEmpty(Object object, String message, String code) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(message,code);
        }
    }

    public static void isEmpty(Object object, ExceptionEnum errorCode) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode);
        }
    }

    public static void isEmpty(Object object, ExceptionEnum errorCode, String info) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode, info);
        }
    }


    public static void isEmpty(Collection<?> collection, String message) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new BusinessException(message);
        }
    }
    public static void isEmpty(Collection<?> collection, String message,String code) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new BusinessException(message,code);
        }
    }

    public static void isEmpty(Collection<?> collection, ExceptionEnum errorCode, String info) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isEmpty(Collection<?> collection, ExceptionEnum errorCode) {
        if (CollectionUtil.isEmpty(collection)) {
            throw new BusinessException(errorCode);
        }
    }


    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }
    public static void isBlank(String str, String message,String code) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message,code);
        }
    }

    public static void isBlank(String str, ExceptionEnum errorCode, String info) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isBlank(String str, ExceptionEnum errorCode) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(errorCode);
        }
    }

    public static void isAnyBlank(String message, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(message);
        }
    }

    public static void isAnyBlank(String message,String code, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(message,code);
        }
    }

    public static void isAnyBlank(String info, ExceptionEnum errorCode, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isAnyBlank(ExceptionEnum errorCode, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(errorCode);
        }
    }


    public static void isEmail(String email, String message) {
        if (Validator.isEmail(email)) {
            throw new BusinessException(message);
        }
    }
    public static void isEmail(String email, String message,String code) {
        if (Validator.isEmail(email)) {
            throw new BusinessException(message,code);
        }
    }

    public static void isEmail(String email, ExceptionEnum errorCode, String info) {
        if (Validator.isEmail(email)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isEmail(String email, ExceptionEnum errorCode) {
        if (Validator.isEmail(email)) {
            throw new BusinessException(errorCode);
        }
    }

    public static void isMobile(String phone, String message) {
        if (Validator.isMobile(phone)) {
            throw new BusinessException(message);
        }
    }
    public static void isMobile(String phone, String message,String code) {
        if (Validator.isMobile(phone)) {
            throw new BusinessException(message,code);
        }
    }
    public static void isMobile(String phone, ExceptionEnum errorCode, String info) {
        if (Validator.isMobile(phone)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isMobile(String phone, ExceptionEnum errorCode) {
        if (Validator.isMobile(phone)) {
            throw new BusinessException(errorCode);
        }
    }


    /**
     *
     * @author: bird
     * @date: 2022-4-15 8:55
     * @param:
     * @return:
     **/
    public static void validated(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for (FieldError error : bindingResult.getFieldErrors()){
                log.error(String.format("[%s %s validated failed!]",error.getField(),error.getCode()));
                throw new BusinessException(ExceptionEnum.ERROR_A0400, error.getDefaultMessage());
            }
        }
    }




}
