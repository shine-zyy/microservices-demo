package com.zyy.springcloud.feignconsumer.controller;

import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.enums.ResultCode;
import org.springframework.util.StringUtils;

public abstract class AbstractController {

    protected <T> BaseResponse<T> buildJson(String code, String msg, T data) {
        BaseResponse<T> baseRes = new BaseResponse<T>();
        boolean ret = ResultCode.SUCCESS.getCode().equals(code);
        baseRes.setSuccess(ret);
        baseRes.setCode(code);
        baseRes.setMsg(msg);
        baseRes.setSuccess(ret);
        if (ret) {
            baseRes.setData(data);
        }
        return baseRes;
    }

    protected <T> BaseResponse<T> buildJson(ResultCode resultCode) {
        BaseResponse<T> baseRes = new BaseResponse<T>();
        boolean ret = ResultCode.SUCCESS.getCode().equals(resultCode.getCode());
        baseRes.setSuccess(ret);
        baseRes.setCode(resultCode.getCode());
        baseRes.setMsg(resultCode.getDesc());
        return baseRes;
    }

    protected <T> BaseResponse<T> buildJson(ResultCode resultCode, String msg) {
        BaseResponse<T> baseRes = new BaseResponse<T>();
        boolean ret = ResultCode.SUCCESS.getCode().equals(resultCode.getCode());
        baseRes.setSuccess(ret);
        baseRes.setCode(resultCode.getCode());
        baseRes.setMsg(StringUtils.isEmpty(msg) ? resultCode.getDesc() : msg);
        return baseRes;
    }
}
