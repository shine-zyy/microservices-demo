package com.zyy.springcloud.api.controller;

import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.enums.ResultCode;

public abstract class AbstractController {

    protected <T> BaseResponse<T> buildJson(Integer code, String msg, T data) {
        BaseResponse<T> baseRes = new BaseResponse<T>();
        boolean isSuccess = ResultCode.SUCCESS.getCode().equals(code);
        baseRes.setSuccess(isSuccess);
        baseRes.setCode(code);
        baseRes.setMsg(msg);
        if (isSuccess && null != data) {
            baseRes.setData(data);
        }
        return baseRes;
    }

    protected <T> BaseResponse<T> buildJson(ResultCode resultCode) {
        return buildJson(resultCode.getCode(), resultCode.getDesc(), null);
    }

    protected <T> BaseResponse<T> buildJson(Integer code, String msg) {
        return buildJson(code, msg, null);
    }

}
