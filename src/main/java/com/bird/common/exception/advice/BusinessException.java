package com.bird.common.exception.advice;

import com.bird.common.exception.enums.ErrorCodeEnum;
import com.bird.common.exception.enums.DefaultCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * 通用业务异常 {@link RuntimeException}
 * @author bird
 * @date 2021-7-21 9:34
 **/
public class BusinessException extends RuntimeException {


    private final String code ;
    private final String msg;



    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }


    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(ErrorCodeEnum errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
    public BusinessException(ErrorCodeEnum errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    /**
     * 使用CommonErrorCode枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(DefaultCodeEnum errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = String.valueOf(errorCode.getCode());
        this.msg = errorCode.getMsg();
    }

    public BusinessException(DefaultCodeEnum errorCode, String msg) {
        super(msg);
        this.code = String.valueOf(errorCode.getCode());
        this.msg = errorCode.getMsg();
    }

    /**
     * 使用自定义消息
     * @param code 值
     * @param msg  详情
     */
    public BusinessException(String msg, String code) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = code;
        this.msg = msg;
    }

    /**
     * 使用自定义消息
     * 通用未知异常
     * @param msg 值
     */
    public BusinessException(String msg) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
        this.msg = msg;
    }
}
