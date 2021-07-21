package com.bird.common.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bird.common.exception.details.DefaultCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author bird
 * @date 2021-7-21 8:47
 **/
public class Result<T> implements Serializable {
    /**
     * 服务器当前时间戳
     */
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date());

    /**
     * 错误码
     */
    private int status;
    /**
     * 错误描述
     */
    private String message;

    /**
     * 提示码
     */
    private String code;
    /**
     * 提示信息
     */
    private String info;

    /**
     * 返回数据
     */
    private T data;





    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }


    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public Result() {
    }

    public Result(String timestamp, T data,  int status, String message, String code, String info) {
        this.timestamp = timestamp;
        this.data = data;

        this.status = status;
        this.info = info;
        this.code = code;
        this.message = message;
    }

    public static Result ofSuccess() {
        Result result = new Result();

        result.status= HttpStatus.OK.value();
        result.message = HttpStatus.OK.getReasonPhrase();
        result.code = DefaultCode.SUCCESS.getCode();
        result.info = DefaultCode.SUCCESS.getInfo();

        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();

        result.status= HttpStatus.OK.value();
        result.message = HttpStatus.OK.getReasonPhrase();
        result.code = DefaultCode.SUCCESS.getCode();
        result.info = DefaultCode.SUCCESS.getInfo();

        result.setData(data);

        return result;
    }

    public static Result ofFail(int status, String message, String code, String info) {
        Result result = new Result();
        result.status = status;
        result.message = message;

        result.code = code;
        result.info = info;

        return result;
    }

    public static Result ofFail(int status, String message, String code, String info, Object data) {
        Result result = new Result();

        result.status = status;
        result.message = message;
        result.code = code;
        result.info = info;

        result.setData(data);

        return result;
    }

    public static Result ofFail(DefaultCode resultEnum) {
        Result result = new Result();

        result.status = resultEnum.getStatus();
        result.message = resultEnum.getMessage();
        result.code = resultEnum.getCode();
        result.info = resultEnum.getInfo();
        return result;
    }

    /**
     * 获取 json
     * @return json
     */
    public String buildResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp", this.timestamp);
        jsonObject.put("status", this.status);
        jsonObject.put("message", this.message);
        jsonObject.put("code", this.code);
        jsonObject.put("info", this.info);

        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +'\'' +
                ", status='" + code +
                ", message='" + message +
                ", code='" + code +
                ", info='" + info +
                ", data=" + data + '\'' +
                '}';
    }
}
