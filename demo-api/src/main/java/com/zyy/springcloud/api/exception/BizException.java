package com.zyy.springcloud.api.exception;

/**
 * @ClassName BizException
 * @Description 业务异常定义
 */
public class BizException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public BizException(String code, String message) {
        super(code, message);
    }

    public BizException(String message) {
        super(message);
    }
}
