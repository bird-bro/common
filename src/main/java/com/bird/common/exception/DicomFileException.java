package com.bird.common.exception;


import org.springframework.http.HttpStatus;

/**
 * Dicom文件异常
 * @author bird
 * @date 2022-4-7 15:24
 **/
public class DicomFileException extends RuntimeException{

    private static final long serialVersionUID = 123231L;
    private String msg;
    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    public DicomFileException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DicomFileException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public DicomFileException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public DicomFileException(String msg, int code, Throwable e) {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
