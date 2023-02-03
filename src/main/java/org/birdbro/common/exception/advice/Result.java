package org.birdbro.common.exception.advice;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import org.birdbro.common.enums.ExceptionEnum;
import org.birdbro.common.enums.HttpStatusEnum;
import lombok.Getter;
import lombok.Setter;

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



    public Result() {
    }

    public Result(String timestamp, T data,  String code, String msg){
        this.timestamp = timestamp;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


     /**
     * 200 处理成功
     **/
    public static Result ofSuccess(Object data) {
        Result result = new Result();

        result.code= String.valueOf(HttpStatusEnum.SUCCESS.getCode());
        result.msg = HttpStatusEnum.SUCCESS.getMsg();
        result.setData(data);

        return result;
    }

    /**
     * 204 请求已经执行成功，但没有内容
     **/
    public static Result ofSuccess() {
        Result result = new Result();

        result.code= String.valueOf(HttpStatusEnum.NO_CONTENT.getCode());
        result.msg = HttpStatusEnum.NO_CONTENT.getMsg();
        return result;
    }



    public static Result ofFail(HttpStatusEnum httpStatusEnum) {
        Result result = new Result();
        result.code = String.valueOf(httpStatusEnum.getCode());
        result.msg = httpStatusEnum.getMsg();
        return result;
    }

    public static Result ofFail(ExceptionEnum exceptionEnum) {
        Result result = new Result();
        result.code = exceptionEnum.getCode();
        result.msg = exceptionEnum.getMsg();
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
        return JSON.toJSONString(jsonObject, JSONWriter.Feature.BeanToArray);
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
