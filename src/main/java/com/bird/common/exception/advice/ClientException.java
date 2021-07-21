package com.bird.common.exception.advice;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * @author bird
 * @date 2021-7-21 14:14
 **/
@Slf4j
public class ClientException extends Exception{
    private static final long serialVersionUID = -7697654244064441234L;
    protected int errorCode;
    protected String message;
    protected Object errorObject;
    protected ErrorType errorType;

    public ClientException(String message) {
        this(0, message, (Throwable)null);
    }

    public ClientException(int errorCode) {
        this(errorCode, (String)null, (Throwable)null);
    }

    public ClientException(int errorCode, String message) {
        this(errorCode, message, (Throwable)null);
    }

    public ClientException(Throwable chainedException) {
        this(0, (String)null, chainedException);
    }

    public ClientException(String message, Throwable chainedException) {
        this(0, message, chainedException);
    }

    public ClientException(int errorCode, String message, Throwable chainedException) {
        super(message == null && errorCode != 0 ? ", code=" + errorCode + "->" + ErrorType.getName(errorCode) : message, chainedException);
        this.errorType = ErrorType.GENERAL;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ClientException(ErrorType error) {
        this(error.ordinal(), (String)null, (Throwable)null);
        this.errorType = error;
    }

    public ClientException(ErrorType error, String message) {
        this(error.ordinal(), message, (Throwable)null);
        this.errorType = error;
    }

    public ClientException(ErrorType error, String message, Throwable chainedException) {
        super(message == null && error.ordinal() != 0 ? ", code=" + error.ordinal() + "->" + error.name() : message, chainedException);
        this.errorType = ErrorType.GENERAL;
        this.errorCode = error.ordinal();
        this.message = message;
        this.errorType = error;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.message;
    }

    public void setErrorMessage(String msg) {
        this.message = msg;
    }

    public Object getErrorObject() {
        return this.errorObject;
    }

    public void setErrorObject(Object errorObject) {
        this.errorObject = errorObject;
    }

    public String getInternalMessage() {
        return "{no message: " + this.errorCode + "}";
    }

    public static HashMap getErrorCodes(Class clazz) {
        HashMap map = new HashMap(23);
        Field[] flds = clazz.getDeclaredFields();

        for(int i = 0; i < flds.length; ++i) {
            int mods = flds[i].getModifiers();
            if (Modifier.isFinal(mods) && Modifier.isStatic(mods) && Modifier.isPublic(mods)) {
                try {
                    map.put(flds[i].get((Object)null), flds[i].getName());
                } catch (Throwable var6) {
                }
            }
        }

        return map;
    }

    public static enum ErrorType {
        GENERAL,
        CONFIGURATION,
        NUMBEROF_RETRIES_EXEEDED,
        NUMBEROF_RETRIES_NEXTSERVER_EXCEEDED,
        SOCKET_TIMEOUT_EXCEPTION,
        READ_TIMEOUT_EXCEPTION,
        UNKNOWN_HOST_EXCEPTION,
        CONNECT_EXCEPTION,
        CLIENT_THROTTLED,
        SERVER_THROTTLED,
        NO_ROUTE_TO_HOST_EXCEPTION,
        CACHE_MISSING;

        private ErrorType() {
        }

        static String getName(int errorCode) {
            return values().length >= errorCode ? values()[errorCode].name() : "UNKNOWN ERROR CODE";
        }
    }
}
