package com.bird.common.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bird.common.exception.enums.DefaultCodeEnum;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 统一返回
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
    private int httpStatus;
    /**
     * 错误描述
     */
    private String message;

    /**
     * 提示码
     */
    private String status;
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


    public int getHttpStatus() {
        return httpStatus;
    }
    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public Result() {
    }

    public Result(String timestamp, T data,  int httpStatus, String message, String status, String info) {
        this.timestamp = timestamp;
        this.data = data;

        this.httpStatus = httpStatus;
        this.info = info;
        this.status = status;
        this.message = message;
    }

    public static Result ofSuccess() {
        Result result = new Result();

        result.httpStatus= HttpStatus.OK.value();
        result.message = HttpStatus.OK.getReasonPhrase();
        result.status = DefaultCodeEnum.SUCCESS.getCode();
        result.info = DefaultCodeEnum.SUCCESS.getInfo();

        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();

        result.httpStatus= HttpStatus.OK.value();
        result.message = HttpStatus.OK.getReasonPhrase();
        result.status = DefaultCodeEnum.SUCCESS.getCode();
        result.info = DefaultCodeEnum.SUCCESS.getInfo();

        result.setData(data);

        return result;
    }

    public static Result ofFail(int httpStatus, String message, String status, String info) {
        Result result = new Result();
        result.httpStatus = httpStatus;
        result.message = message;

        result.status = status;
        result.info = info;

        return result;
    }

    public static Result ofFail(int httpStatus, String message, String status, String info, Object data) {
        Result result = new Result();

        result.httpStatus = httpStatus;
        result.message = message;
        result.status = status;
        result.info = info;

        result.setData(data);

        return result;
    }

    public static Result ofFail(DefaultCodeEnum resultEnum) {
        Result result = new Result();

        result.httpStatus = resultEnum.getStatus();
        result.message = resultEnum.getMessage();
        result.status = resultEnum.getCode();
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
        jsonObject.put("httpStatus", this.httpStatus);
        jsonObject.put("message", this.message);
        jsonObject.put("status", this.status);
        jsonObject.put("info", this.info);

        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +'\'' +
                ", httpStatus='" + httpStatus +
                ", message='" + message +
                ", status='" + status +
                ", info='" + info +
                ", data=" + data + '\'' +
                '}';
    }
}
