package org.birdbro.common.exception;


import org.birdbro.common.enums.ExceptionEnum;
import org.birdbro.common.enums.HttpStatusEnum;
import org.springframework.http.HttpStatus;

/**
 * 警告异常
 * @author bird
 * @date 2022-4-7 15:24
 **/
public class WarnException extends RuntimeException{

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
    public WarnException(ExceptionEnum errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }
    public WarnException(ExceptionEnum errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }


    /**
     * 使用CommonErrorCode枚举传参
     *
     * @param errorCode 异常枚举
     */
    public WarnException(HttpStatusEnum errorCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        this.code = String.valueOf(errorCode.getCode());
        this.msg = errorCode.getMsg();
    }

    public WarnException(HttpStatusEnum errorCode, String msg) {
        super(msg);
        this.code = String.valueOf(errorCode.getCode());
        this.msg = errorCode.getMsg();
    }


    /**
     * 使用自定义消息
     * @param code 值
     * @param msg  详情
     */
    public WarnException(String msg, String code) {
        super(HttpStatus.NO_CONTENT.getReasonPhrase());
        this.code = code;
        this.msg = msg;
    }

    /**
     * 使用自定义消息
     * 通用未知异常
     * @param msg 值
     */
    public WarnException(String msg) {
        super(HttpStatus.NO_CONTENT.getReasonPhrase());
        this.code = String.valueOf(HttpStatus.NO_CONTENT.value());
        this.msg = msg;
    }

}
