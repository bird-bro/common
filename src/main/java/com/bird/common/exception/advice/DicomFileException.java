package com.bird.common.exception.advice;


import org.springframework.http.HttpStatus;

/**
 * Dicom文件异常
 * @author bird
 * @date 2022-4-7 15:24
 **/
public class DicomFileException extends RuntimeException{

    private static final long serialVersionUID = 123231L;
    private String msg;
    private String code = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());

    public DicomFileException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DicomFileException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public DicomFileException(String msg, String code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public DicomFileException(String msg, String code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
