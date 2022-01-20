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

    private final int status;
    private String message;
    private final String code ;
    private final String info;


    public int getStatus() {
        return status;
    }
    public String getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }


    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(ErrorCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }
    public BusinessException(String message, ErrorCodeEnum errorCode) {
        super(message);
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }

    /**
     * 使用CommonErrorCode枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(DefaultCodeEnum errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }
    public BusinessException(String message, DefaultCodeEnum errorCode) {
        super(message);
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }

    /**
     * 使用自定义消息
     *
     * @param message 值
     * @param code 值
     * @param info  详情
     */
    public BusinessException(String message, String code, String info) {
        super(message+"-----"+info);

        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = code;
        this.info = info;
    }

    /**
     * 使用自定义消息
     * @param code 值
     * @param info  详情
     */
    public BusinessException(String code,String info) {
        super("--"+HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = code;
        this.info = info;
    }

    /**
     * 使用自定义消息
     * 通用未知异常
     * @param info 值
     */
    public BusinessException(String info) {
        super(info);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = "ERROR";
        this.info = info;
    }
}
