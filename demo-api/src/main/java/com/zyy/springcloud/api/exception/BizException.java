package com.zyy.springcloud.api.exception;

import com.zyy.springcloud.api.model.enums.ResultCode;

/**
 * @ClassName BizException
 * @Description 业务异常定义
 */
public class BizException extends BaseRuntimeException {
    private static final long serialVersionUID = 1L;

    public BizException(Integer code, String message) {
        super(code, message);
    }

    public BizException(ResultCode resultCode) {
        super(resultCode.getCode(), resultCode.getDesc());
    }

}
