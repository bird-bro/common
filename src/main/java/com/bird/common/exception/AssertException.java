package com.bird.common.exception;

import com.bird.common.exception.advice.BusinessException;
import com.bird.common.enums.BusinessEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author bird
 * @date 2021-7-21 9:58
 **/
@Slf4j
public class AssertException {

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

    public static void isEmpty(Object object, BusinessEnum errorCode) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode);
        }
    }

    public static void isEmpty(Object object, BusinessEnum errorCode, String info) {
        if (ObjectUtils.isEmpty(object)) {
            throw new BusinessException(errorCode, info);
        }
    }


    public static void isNotEmpty(Object object, String message) {
        if (ObjectUtils.isNotEmpty(object)) {
            throw new BusinessException(message);
        }
    }
    public static void isNotEmpty(Object object, String message,String code) {
        if (ObjectUtils.isNotEmpty(object)) {
            throw new BusinessException(message, code);
        }
    }

    public static void isNotEmpty(Object object, BusinessEnum errorCode, String info) {
        if (ObjectUtils.isNotEmpty(object)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isNotEmpty(Object object, BusinessEnum errorCode) {
        if (ObjectUtils.isNotEmpty(object)) {
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

    public static void isBlank(String str, BusinessEnum errorCode, String info) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isBlank(String str, BusinessEnum errorCode) {
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

    public static void isAnyBlank(String info, BusinessEnum errorCode, String... css) {
        if (StringUtils.isAnyBlank(css)) {
            throw new BusinessException(errorCode, info);
        }
    }
    public static void isAnyBlank(BusinessEnum errorCode, String... css) {
        if (StringUtils.isAnyBlank(css)) {
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
                throw new BusinessException(BusinessEnum.ERROR_A0400, error.getDefaultMessage());
            }
        }
    }




}
