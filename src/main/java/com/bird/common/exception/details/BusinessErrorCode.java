package com.bird.common.exception.details;

import org.springframework.http.HttpStatus;

/**
 * 业务通用异常枚举
 * @author bird
 * @date 2021-7-21 9:28
 **/
public enum BusinessErrorCode {

    /**
     * 通用业务异常
     * ERROR 错误的请求
     */
    BUSINESS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "ERROR", String.format("系统执行出错(%s)",HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())),

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
     * B类 二级宏观错误码
     */
    BUSINESS_ERROR_B0100(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0100", "系统执行超时"),
    BUSINESS_ERROR_B0200(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0200", "系统容灾功能被触发"),
    BUSINESS_ERROR_B0300(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0300", "系统资源异常"),
    /**
     * C类 二级宏观错误码
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



    /**
     * A类 错误码
     */
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
    BUSINESS_ERROR_A0310(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0310", "因访问对象隐私设置被拦截"),
    BUSINESS_ERROR_A0311(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0311", "授权已过期"),
    BUSINESS_ERROR_A0312(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0312", "无权限使用API"),
    BUSINESS_ERROR_A0320(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0320", "用户访问被拦截"),
    BUSINESS_ERROR_A0321(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0321", "黑名单用户"),
    BUSINESS_ERROR_A0322(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0322", "账号被冻结"),
    BUSINESS_ERROR_A0323(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0323", "非法IP地址"),
    BUSINESS_ERROR_A0324(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0324", "网关访问受限"),
    BUSINESS_ERROR_A0325(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0325", "地域黑名单"),
    BUSINESS_ERROR_A0330(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0330", "服务已欠费"),
    BUSINESS_ERROR_A0340(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0340", "用户签名异常"),
    BUSINESS_ERROR_A0341(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0341", "RSA签名错误"),

    BUSINESS_ERROR_A0401(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0401", "包含非法恶意跳转链接"),
    BUSINESS_ERROR_A0402(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0402", "无效的用户输入"),

    BUSINESS_ERROR_A0410(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0410", "请求必填参数为空"),
    BUSINESS_ERROR_A0411(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0411", "用户订单号为空"),
    BUSINESS_ERROR_A0412(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0412", "订购数量为空"),
    BUSINESS_ERROR_A0413(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0413", "缺少时间戳参数"),
    BUSINESS_ERROR_A0414(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0414", "非法的时间戳参数"),
    BUSINESS_ERROR_A0420(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0420", "请求参数值超出允许的范围"),
    BUSINESS_ERROR_A0421(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0421", "参数格式不匹配"),
    BUSINESS_ERROR_A0422(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0422", "地址不在服务范围"),
    BUSINESS_ERROR_A0423(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0423", "时间不在服务范围"),
    BUSINESS_ERROR_A0424(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0424", "金额超出限制"),
    BUSINESS_ERROR_A0425(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0425", "数量超出限制"),
    BUSINESS_ERROR_A0426(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0426", "请求批量处理总个数超出限制"),
    BUSINESS_ERROR_A0427(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0427", "请求JSON解析失败"),

    BUSINESS_ERROR_A0430(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0430", "用户输入内容非法"),
    BUSINESS_ERROR_A0431(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0431", "包含违禁敏感词"),
    BUSINESS_ERROR_A0432(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0432", "图片包含违禁信息"),
    BUSINESS_ERROR_A0433(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0433", "文件侵犯版权"),
    BUSINESS_ERROR_A0440(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0440", "用户操作异常"),
    BUSINESS_ERROR_A0441(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0441", "用户支付超时"),
    BUSINESS_ERROR_A0442(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0442", "确认订单超时"),
    BUSINESS_ERROR_A0443(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0443", "订单已关闭"),

    BUSINESS_ERROR_A0501(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0501", "请求次数超出限制"),
    BUSINESS_ERROR_A0502(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0502", "请求并发数超出限制"),
    BUSINESS_ERROR_A0503(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0503", "用户操作请等待"),
    BUSINESS_ERROR_A0504(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0504", "WebSocket连接异常"),
    BUSINESS_ERROR_A0505(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0505", "WebSocket连接断开"),
    BUSINESS_ERROR_A0506(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0506", "用户重复请求"),

    BUSINESS_ERROR_A0601(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0601", "账户余额不足"),
    BUSINESS_ERROR_A0602(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0602", "用户磁盘空间不足"),
    BUSINESS_ERROR_A0603(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0603", "用户内存空间不足"),
    BUSINESS_ERROR_A0604(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0604", "用户存储空间不足"),
    BUSINESS_ERROR_A0605(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0605", "用户配额已用完"),

    BUSINESS_ERROR_A0701(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0701", "用户上传文件类型不匹配"),
    BUSINESS_ERROR_A0702(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0702", "用户上传文件太大"),
    BUSINESS_ERROR_A0703(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0703", "用户上传图片太大"),
    BUSINESS_ERROR_A0704(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0704", "用户上传视频太大"),
    BUSINESS_ERROR_A0705(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0705", "用户上传压缩文件太大"),

    BUSINESS_ERROR_A0801(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0801", "用户安装版本与系统不匹配"),
    BUSINESS_ERROR_A0802(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0802", "用户安装版本过低"),
    BUSINESS_ERROR_A0803(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0803", "用户安装版本过高"),
    BUSINESS_ERROR_A0804(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0804", "用户安装版本已过期"),
    BUSINESS_ERROR_A0805(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0805", "用户API请求版本不匹配"),
    BUSINESS_ERROR_A0806(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0806", "用户API请求版本过高"),
    BUSINESS_ERROR_A0807(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0807", "用户API请求版本过低"),

    BUSINESS_ERROR_A0901(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0901", "用户隐私未签署"),
    BUSINESS_ERROR_A0902(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0902", "用户摄像头未授权"),
    BUSINESS_ERROR_A0903(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0903", "用户相机未授权"),
    BUSINESS_ERROR_A0904(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0904", "用户图片库未授权"),
    BUSINESS_ERROR_A0905(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0905", "用户文件未授权"),
    BUSINESS_ERROR_A0906(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0906", "用户位置信息未授权"),
    BUSINESS_ERROR_A0907(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A0907", "用户通讯录未授权"),

    BUSINESS_ERROR_A1001(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1001", "用户相机异常"),
    BUSINESS_ERROR_A1002(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1002", "用户麦克风异常"),
    BUSINESS_ERROR_A1003(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1003", "用户听筒异常"),
    BUSINESS_ERROR_A1004(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1004", "用户扬声器异常"),
    BUSINESS_ERROR_A1005(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "A1005", "用户GPS定位异常"),




    /**
     * B类--错误码
     */
    BUSINESS_ERROR_B0101(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0101", "系统订单处理超时"),
    BUSINESS_ERROR_B0210(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0210", "系统限流"),
    BUSINESS_ERROR_B0220(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0220", "系统功能降级"),

    BUSINESS_ERROR_B0310(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0310", "系统资源访问异常"),
    BUSINESS_ERROR_B0311(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0311", "系统磁盘空间耗尽"),
    BUSINESS_ERROR_B0312(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0312", "系统内存耗尽"),
    BUSINESS_ERROR_B0313(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0313", "文件句柄耗尽"),
    BUSINESS_ERROR_B0314(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0314", "系统连接池耗尽"),
    BUSINESS_ERROR_B0315(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0315", "系统线程池耗尽"),

    BUSINESS_ERROR_B0320(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0320", "系统资源访问异常"),
    BUSINESS_ERROR_B0321(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "B0321", "系统读取磁盘文件失败"),



    BUSINESS_ERROR_C0110(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0110", "RPC服务出错"),
    BUSINESS_ERROR_C0111(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0111", "RPC服务未找到"),
    BUSINESS_ERROR_C0112(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0112", "RPC服务未注册"),
    BUSINESS_ERROR_C0113(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0113", "接口不存在"),

    BUSINESS_ERROR_C0120(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0120", "消息服务出错"),
    BUSINESS_ERROR_C0121(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0121", "消息投递出错"),
    BUSINESS_ERROR_C0122(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0122", "消息消费出错"),
    BUSINESS_ERROR_C0123(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0123", "消息订阅出错"),
    BUSINESS_ERROR_C0124(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0124", "消息分组未查到"),

    BUSINESS_ERROR_C0130(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0130", "缓存服务出错"),
    BUSINESS_ERROR_C01310(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0131", "key长度超过限制"),
    BUSINESS_ERROR_C0132(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0132", "value长度超过限制"),
    BUSINESS_ERROR_C0133(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0133", "存储容量已满"),
    BUSINESS_ERROR_C0134(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0134", "不支持的数据格式"),

    BUSINESS_ERROR_C0140(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0140", "配置服务出错"),
    BUSINESS_ERROR_C0150(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0150", "网络资源服务出错"),
    BUSINESS_ERROR_C0151(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0151", "VPN服务出错"),
    BUSINESS_ERROR_C0152(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0152", "CDN服务出错"),
    BUSINESS_ERROR_C0153(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0153", "域名解析服务出错"),
    BUSINESS_ERROR_C0154(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0154", "网关服务出错"),

    BUSINESS_ERROR_C0210(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0210", "RPC执行超时"),
    BUSINESS_ERROR_C0220(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0220", "消息投递超时"),
    BUSINESS_ERROR_C0230(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0230", "缓存服务超时"),
    BUSINESS_ERROR_C0240(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0240", "配置服务超时"),
    BUSINESS_ERROR_C0250(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0250", "数据库服务超时"),

    BUSINESS_ERROR_C0311(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0311", "表不存在"),
    BUSINESS_ERROR_C0312(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0312", "列不存在"),
    BUSINESS_ERROR_C0321(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0321", "多表关联中存在多个相同名称列"),
    BUSINESS_ERROR_C0331(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0331", "数据库死锁"),
    BUSINESS_ERROR_C0341(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0341", "主键冲突"),

    BUSINESS_ERROR_C0401(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0401", "第三方系统限流"),
    BUSINESS_ERROR_C0402(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0402", "第三方功能降级"),

    BUSINESS_ERROR_C0501(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0501", "短信提醒服务失败"),
    BUSINESS_ERROR_C0502(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0502", "语音提醒服务失败"),
    BUSINESS_ERROR_C0503(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "C0503", "邮件提醒服务失败"),

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
