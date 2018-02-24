package com.zyy.springcloud.api.exception;


public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public BaseException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        super();
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
