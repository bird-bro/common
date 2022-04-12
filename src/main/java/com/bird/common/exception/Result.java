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
    private int httpCode;
    /**
     * 错误描述
     */
    private String httpMsg;

    /**
     * 状态码
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


    public int getHttpCode() {
        return httpCode;
    }
    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
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

    public String getHttpMsg() {
        return httpMsg;
    }
    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }


    public Result() {
    }

    public Result(String timestamp, T data,  int httpCode, String httpMsg, String status, String info) {
        this.timestamp = timestamp;
        this.data = data;

        this.httpCode = httpCode;
        this.info = info;
        this.status = status;
        this.httpMsg = httpMsg;
    }

    public static Result ofSuccess() {
        Result result = new Result();

        result.httpCode= HttpStatus.OK.value();
        result.httpMsg = HttpStatus.OK.getReasonPhrase();
        result.status = DefaultCodeEnum.SUCCESS.getCode();
        result.info = DefaultCodeEnum.SUCCESS.getInfo();

        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();

        result.httpCode= HttpStatus.OK.value();
        result.httpMsg = HttpStatus.OK.getReasonPhrase();
        result.status = DefaultCodeEnum.SUCCESS.getCode();
        result.info = DefaultCodeEnum.SUCCESS.getInfo();

        result.setData(data);

        return result;
    }

    public static Result ofFail(int httpCode, String httpMsg, String status, String info) {
        Result result = new Result();
        result.httpCode = httpCode;
        result.httpMsg = httpMsg;

        result.status = status;
        result.info = info;

        return result;
    }

    public static Result ofFail(int httpCode, String httpMsg, String status, String info, Object data) {
        Result result = new Result();

        result.httpCode = httpCode;
        result.httpMsg = httpMsg;
        result.status = status;
        result.info = info;

        result.setData(data);

        return result;
    }

    public static Result ofFail(DefaultCodeEnum resultEnum) {
        Result result = new Result();

        result.httpCode = resultEnum.getHttpCode();
        result.httpMsg = resultEnum.getHttpMsg();
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
        jsonObject.put("httpCode", this.httpCode);
        jsonObject.put("httpMsg", this.httpMsg);
        jsonObject.put("status", this.status);
        jsonObject.put("info", this.info);

        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +'\'' +
                ", httpCode='" + httpCode +
                ", httpMsg='" + httpMsg +
                ", status='" + status +
                ", info='" + info +
                ", data=" + data + '\'' +
                '}';
    }
}
