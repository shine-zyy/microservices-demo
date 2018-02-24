package com.zyy.springcloud.api.exception;


public class ParamException extends BaseRuntimeException {
    private static final long serialVersionUID = -8990852167849110611L;

    public ParamException(Integer code, String message) {
        super(code, message);
    }

    public ParamException(String message) {
        super(message);
    }


}
