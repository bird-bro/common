package com.dispose.exception.details;

import org.springframework.http.HttpStatus;

/**
 * 异常枚举
 * @Description TODO
 * @Date 16:17 2020/6/24
 * @Created wangpeng
 */
public enum CommonErrorCode {

    /**
     * 200 处理成功。
     */
    SUCCESS(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
            "00000","一切OK"),


    NO_CONTENT(HttpStatus.NO_CONTENT.value(),HttpStatus.NO_CONTENT.getReasonPhrase(),
            "E204","没有查询到符合条件的记录"),

    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),
            "E404", "服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。"),

    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(),HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
            "A0420","对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。"),

    /**
     * 415 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(),
            "A0700","不支持的媒体类型"),

    /**
     * 系统异常 500 服务器的内部错误
     */
    EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0001", "服务器的内部错误"),

    /**
     * 系统限流
     */
    TRAFFIC_LIMITING(HttpStatus.TOO_MANY_REQUESTS.value(),HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(),
            "B0210","网络拥繁忙，稍后再试试"),

    /**
     * 400 错误的请求
     */
    BUSINESS_ERROR(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "A0001", "错误的请求"),

    /**
     * 407非法请求
     */
    ILLEGAL_REQUEST(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value(),HttpStatus.PROXY_AUTHENTICATION_REQUIRED.getReasonPhrase(),
            "A0500","非法请求"),

    /**
     * rpc调用异常
     */
    RPC_ERROR(HttpStatus.NOT_EXTENDED.value(),HttpStatus.NOT_EXTENDED.getReasonPhrase(),
            "C0110", "RPC调用异常"),

    /**
     * 参数校验异常
     */
    PARAM_ERROR(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "A0001", String.format("错误的请求(%s)",HttpStatus.BAD_REQUEST.getReasonPhrase()))

    ;



    private final int status;
    private final String message;
    private final String code;
    private final String info;

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public String getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }

    CommonErrorCode(int status, String message, String code, String info) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.info = info;
    }

}
