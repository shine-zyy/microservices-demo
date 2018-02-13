package com.zyy.springcloud.feignconsumer.exception;

import com.zyy.springcloud.api.exception.BizException;
import com.zyy.springcloud.api.exception.ParamException;
import com.zyy.springcloud.api.model.dto.response.BaseResponse;
import com.zyy.springcloud.api.model.enums.ResultCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 统一异常处理
 *
 * @author zhangyanyan
 * @date 2018/1/3
 */
@ControllerAdvice(annotations = RestController.class)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {ParamException.class, BizException.class})
    protected ResponseEntity<Object> handleExceptions(Exception ex) {

        if (ex instanceof ParamException) {
            return handleParamException((ParamException) ex);
        } else if (ex instanceof BizException) {
            return handleBizException((BizException) ex);
        } else {
            return new ResponseEntity<Object>(BaseResponse.systemError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Object> handleBizException(BizException ex) {
        return new ResponseEntity<Object>(BaseResponse.fail(ex.getCode(), ex.getMessage()), new HttpHeaders(), HttpStatus.OK);
    }

    private ResponseEntity<Object> handleParamException(ParamException ex) {
        return new ResponseEntity<Object>(BaseResponse.fail(ex.getCode(), ex.getMessage()), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleOtherExceptions(Exception ex) {
        logger.error("Rest Exception!", ex);
        return new ResponseEntity<Object>(BaseResponse.systemError(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Rest BindException!", ex);
        BindingResult bindingResult = ex.getBindingResult();
        String resultMessage = StringUtils.defaultString(bindingResult.getFieldError().getDefaultMessage(), ResultCode.PARAM_ERROR.getDesc());
        return new ResponseEntity<Object>(BaseResponse.fail(ResultCode.PARAM_ERROR.getCode(), resultMessage), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Rest MissingServletRequestParameterException!", ex);
        return new ResponseEntity<Object>(BaseResponse.fail(ResultCode.PARAM_ERROR.getCode(), ex.getMessage()), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("Rest MethodArgumentNotValidException!", ex);
        BindingResult bindingResult = ex.getBindingResult();
        String resultMessage = StringUtils.defaultString(bindingResult.getFieldError().getDefaultMessage(), ResultCode.PARAM_ERROR.getDesc());
        return new ResponseEntity<Object>(BaseResponse.fail(ResultCode.PARAM_ERROR.getCode(), resultMessage), new HttpHeaders(), HttpStatus.OK);
    }
}