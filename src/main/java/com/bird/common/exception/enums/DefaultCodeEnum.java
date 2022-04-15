package com.bird.common.exception.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author bird
 * @date 2021-7-21 9:15
 **/
@Getter
public enum DefaultCodeEnum {

    /**
     * 200 处理成功。
     */
    SUCCESS(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()),


    NO_CONTENT(HttpStatus.NO_CONTENT.value(),HttpStatus.NO_CONTENT.getReasonPhrase()),

    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase()),

    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(),HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()),

    /**
     * 415 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()),

    /**
     * 系统异常 500 服务器的内部错误
     */
    EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),

    /**
     * 系统限流
     */
    TRAFFIC_LIMITING(HttpStatus.TOO_MANY_REQUESTS.value(),HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()),

    /**
     * 400 错误的请求
     */
    BUSINESS_ERROR(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase()),

    /**
     * 407非法请求
     */
    ILLEGAL_REQUEST(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value(),HttpStatus.PROXY_AUTHENTICATION_REQUIRED.getReasonPhrase()),

    /**
     * rpc调用异常
     */
    RPC_ERROR(HttpStatus.NOT_EXTENDED.value(),HttpStatus.NOT_EXTENDED.getReasonPhrase()),

    /**
     * 参数校验异常
     */
    PARAM_ERROR(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase())

    ;


    private final int code;

    private final String msg;

    DefaultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
