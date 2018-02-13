package com.zyy.springcloud.api.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zyy.springcloud.api.model.enums.ResultCode;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = -1223580564248951536L;
	
	private String code;
	private String msg;

	public Result() {
		super();
	}

	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Result success() {
	    return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc());
    }

    public static Result fail(ResultCode resultCode) {
        return new Result(resultCode.getCode(), resultCode.getDesc());
    }

    public void setSuccess(Boolean isSuccess) {
	    if(isSuccess) {
            this.code = ResultCode.SUCCESS.getCode();
            this.msg = ResultCode.SUCCESS.getDesc();
        } else {
            this.code = ResultCode.ERROR.getCode();
            this.msg = ResultCode.ERROR.getDesc();
        }
    }
	@JsonIgnore
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
