package com.bird.dispose.exception.category;

import com.bird.dispose.exception.details.BusinessErrorCode;
import com.bird.dispose.exception.details.CommonErrorCode;
import org.springframework.http.HttpStatus;

/**
 * 通用业务异常 {@link RuntimeException}
 *
 * @author wangpeng
 * @description  TODO
 * @date  16:17 2020/6/24
 */
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
    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }
    public BusinessException(String message,BusinessErrorCode errorCode) {
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
    public BusinessException(CommonErrorCode errorCode) {
        super(errorCode.getMessage());
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.info = errorCode.getInfo();
    }
    public BusinessException(String message,CommonErrorCode errorCode) {
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
        super("BUSINESS_ERROR--"+HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
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
        this.code = "11111";
        this.info = info;
    }



}
