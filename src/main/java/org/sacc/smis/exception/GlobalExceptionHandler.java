package org.sacc.smis.exception;

import org.sacc.smis.enums.Business;
import org.sacc.smis.enums.ResultEnum;
import org.sacc.smis.model.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 * Created by 林夕
 * Date 2021/1/21 20:35
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局处理Exception类型的异常
     */
    @ExceptionHandler(Exception.class)
    public RestResult<ResultEnum> Exception(Exception e) {
        logger.error("未知错误", e);
        return RestResult.error(ResultEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 全局处理NullPointerException类型的异常
     */
    @ExceptionHandler(NullPointerException.class)
    public RestResult<ResultEnum> NullPointerException(NullPointerException e) {
        logger.error("空指针异常", e);
        return RestResult.error(ResultEnum.NULL_POINT);

    }

    /**
     * 捕获请求方式异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public RestResult<ResultEnum> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logger.error("错误请求", e);
        return RestResult.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * 捕获400异常
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public RestResult<ResultEnum> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        logger.error("错误请求", e);
        return RestResult.error(ResultEnum.BAD_REQUEST);
    }

    /**
     * 捕获数据库操作异常
     */
    @ExceptionHandler(value = SQLException.class)
    public RestResult<ResultEnum> SQLExceptionHandler(SQLException e) {
        logger.error("数据库操作异常:", e);
        return RestResult.error(e.getErrorCode(), e.getMessage());
    }

    /**
     * 捕获数据校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public RestResult<ResultEnum> bindExceptionHandler(BindException e) {
        logger.error("数据校验异常", e);
        return RestResult.error(ResultEnum.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * 捕获注册时学号或邮箱重复异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public RestResult<Business> businessRestResult(BusinessException e) {
        logger.error("user校验异常", e);
        return RestResult.error(e.getBusiness().getMessage());
    }

    /**
     * 捕获权限异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public RestResult<ResultEnum> accessDeniedException(AccessDeniedException e) {
        logger.error("您没有权限访问");
        return RestResult.error(ResultEnum.NO_PERMISSION_ACCESS);
    }

    /**
     * 捕获数据库查询异常
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    public RestResult<ResultEnum> noSuchElementException(NoSuchElementException e){
        logger.error("数据库查询异常",e);
        return RestResult.error(ResultEnum.NO_SUCH_ELEMENT);
    }
}
