package com.bird.common.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bird.common.exception.enums.DefaultCodeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 统一返回
 * @author bird
 * @date 2021-7-21 8:47
 **/
@Getter
@Setter
public class Result<T> implements Serializable {
    /**
     * 服务器当前时间戳
     */
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date());

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;





//    public String getTimestamp() {
//        return timestamp;
//    }
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    public T getData() {
//        return data;
//    }
//    public void setData(T data) {
//        this.data = data;
//    }
//
//
//    public int getHttpCode() {
//        return httpCode;
//    }
//    public void setHttpCode(int httpCode) {
//        this.httpCode = httpCode;
//    }
//
//    public String getHttpMsg() {
//        return httpMsg;
//    }
//    public void setHttpMsg(String httpMsg) {
//        this.httpMsg = httpMsg;
//    }


    public Result() {
    }

    public Result(String timestamp, T data,  String code, String msg){
        this.timestamp = timestamp;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ofSuccess() {
        Result result = new Result();

        result.code= String.valueOf(HttpStatus.OK.value());
        result.msg = HttpStatus.OK.getReasonPhrase();
        return result;
    }

    public static Result ofSuccess(Object data) {
        Result result = new Result();

        result.code= String.valueOf(HttpStatus.OK.value());
        result.msg = HttpStatus.OK.getReasonPhrase();
        result.setData(data);

        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        return result;
    }


    public static Result ofFail(String code, String msg, Object data) {
        Result result = new Result();
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }

    public static Result ofFail(DefaultCodeEnum resultEnum) {
        Result result = new Result();
        result.code = String.valueOf(resultEnum.getCode());
        result.msg = resultEnum.getMsg();
        return result;
    }

    /**
     * 获取 json
     * @return json
     */
    public String buildResultJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("timestamp", this.timestamp);
        jsonObject.put("code", this.code);
        jsonObject.put("msg", this.msg);
        jsonObject.put("data", this.data);
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +'\'' +
                ", code='" + code +
                ", msg='" + msg +
                ", data=" + data + '\'' +
                '}';
    }
}
