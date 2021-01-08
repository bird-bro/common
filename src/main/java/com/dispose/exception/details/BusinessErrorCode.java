package com.dispose.exception.details;

import org.springframework.http.HttpStatus;

/**
 * 业务通用异常枚举
 *
 * @Description TODO
 * @Date 16:17 2020/6/24
 * @Created wangpeng
 */
public enum BusinessErrorCode {

    /**
     * 通用业务异常
     * 11111 错误的请求
     */
    BUSINESS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "11111", String.format("系统执行出错(%s)",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())),

    /**
     * 一级宏观错误码
     */
    BUSINESS_ERROR_A0001(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0001", "用户端错误"),
    BUSINESS_ERROR_B0001(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0001", "系统执行出错"),
    BUSINESS_ERROR_C0001(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0001", "调用第三方服务出错"),

    
    /**
     * A类 二级宏观错误码
     */
    BUSINESS_ERROR_A0100(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0100", "用户注册错误"),
    BUSINESS_ERROR_A0200(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0200", "用户登录异常"),
    BUSINESS_ERROR_A0300(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0300", "访问权限异常"),
    BUSINESS_ERROR_A0400(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0400", "用户请求参数错误"),
    BUSINESS_ERROR_A0500(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0500", "用户请求服务异常"),
    BUSINESS_ERROR_A0600(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0600", "用户资源异常"),
    BUSINESS_ERROR_A0700(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0700", "用户上传文件异常"),
    BUSINESS_ERROR_A0800(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0800", "用户当前版本异常"),
    BUSINESS_ERROR_A0900(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0900", "用户隐私未授权"),
    BUSINESS_ERROR_A1000(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1000", "用户设备异常"),

    /**
     * A类 二级宏观错误码
     */
    BUSINESS_ERROR_B0100(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0100", "系统执行超时"),
    BUSINESS_ERROR_B0200(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0200", "系统容灾功能被触发"),
    BUSINESS_ERROR_B0300(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0300", "系统资源异常"),




    /**
     * C类--错误码
     */
    BUSINESS_ERROR_C0100(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0100", "中间件服务出错"),
    BUSINESS_ERROR_C0200(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0200", "第三方系统执行超时"),
    BUSINESS_ERROR_C0300(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0300", "数据库服务出错"),
    BUSINESS_ERROR_C0400(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0400", "第三方容灾系统被触发"),
    BUSINESS_ERROR_C0500(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0500", "通知服务出错"),


    BUSINESS_ERROR_A0101(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0101", "用户未同意隐私协议"),
    BUSINESS_ERROR_A0102(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0102", "注册国家或地区受限"),

    BUSINESS_ERROR_A0110(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0110", "用户名校验失败"),
    BUSINESS_ERROR_A0111(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0111", "用户名已存在"),
    BUSINESS_ERROR_A0112(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0112", "用户名包含敏感词"),
    BUSINESS_ERROR_A0113(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0113", "用户名包含特殊字符"),

    BUSINESS_ERROR_A0120(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0120", "密码校验失败"),
    BUSINESS_ERROR_A0121(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0121", "密码长度不够"),
    BUSINESS_ERROR_A0122(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0122", "密码强度不够"),

    BUSINESS_ERROR_A0130(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0130", "校验码输入错误"),
    BUSINESS_ERROR_A0131(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0131", "短信校验码输入错误"),
    BUSINESS_ERROR_A0132(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0132", "邮件校验码输入错误"),
    BUSINESS_ERROR_A0133(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0133", "语音校验码输入错误"),

    BUSINESS_ERROR_A0140(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0140", "用户证件异常"),
    BUSINESS_ERROR_A0141(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0141", "用户证件类型未选择"),
    BUSINESS_ERROR_A0142(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0142", "大陆身份证编号校验非法"),
    BUSINESS_ERROR_A0143(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0143", "护照编号校验非法"),
    BUSINESS_ERROR_A0144(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0144", "军官证编号校验非法"),

    BUSINESS_ERROR_A0150(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0150", "用户基本信息校验失败"),
    BUSINESS_ERROR_A0151(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0151", "手机格式校验失败"),
    BUSINESS_ERROR_A0152(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0152", "地址格式校验失败"),
    BUSINESS_ERROR_A0153(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0153", "邮箱格式校验失败"),


    BUSINESS_ERROR_A0201(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0201", "用户账户不存在"),
    BUSINESS_ERROR_A0202(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0202", "用户账户被冻结"),
    BUSINESS_ERROR_A0203(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0203", "用户账户已作废"),

    BUSINESS_ERROR_A0210(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0210", "用户密码错误"),
    BUSINESS_ERROR_A0211(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0211", "用户输入密码错误次数超限"),

    BUSINESS_ERROR_A0220(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0220", "用户身份校验失败"),
    BUSINESS_ERROR_A0221(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0221", "用户指纹识别失败"),
    BUSINESS_ERROR_A0222(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0222", "用户面容识别失败"),
    BUSINESS_ERROR_A0223(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0223", "用户未获得第三方登录授权"),

    BUSINESS_ERROR_A0230(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0230", "用户登录已过期"),

    BUSINESS_ERROR_A0240(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0240", "用户验证码错误"),
    BUSINESS_ERROR_A0241(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0241", "用户验证码尝试次数超限"),


    BUSINESS_ERROR_A0301(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0301", "访问未授权"),
    BUSINESS_ERROR_A0302(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0302", "正在授权中"),
    BUSINESS_ERROR_A0303(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0303", "用户授权申请被拒绝"),



    /**
     * B类--错误码
     */
    BUSINESS_ERROR_B0101(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0101", "系统订单处理超时"),
    BUSINESS_ERROR_B0210(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0210", "系统限流"),
    BUSINESS_ERROR_B0220(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0220", "系统功能降级"),
    BUSINESS_ERROR_B0320(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0320", "系统资源访问异常"),








    ;



    /**
     * 错误码
     */
    private final int status;
    /**
     * 错误描述
     */
    private final String message;

    /**
     * 提示码
     */
    private final String code;

    /**
     * 错误提示
     */
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


    BusinessErrorCode(int status, String message , String code, String info) {
        this.status = status;
        this.info = info;
        this.code = code;
        this.message = message;
    }

}
