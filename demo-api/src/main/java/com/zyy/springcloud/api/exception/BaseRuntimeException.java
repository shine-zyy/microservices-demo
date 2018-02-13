package com.zyy.springcloud.api.exception;


public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    public BaseRuntimeException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(String message) {
        super();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
