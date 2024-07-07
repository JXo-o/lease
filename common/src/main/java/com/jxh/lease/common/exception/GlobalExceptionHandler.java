package com.jxh.lease.common.exception;

import com.jxh.lease.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.jxh.lease.common.exception
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/7 13:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     * @param e 自定义异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail();
    }

    @ExceptionHandler(LeaseException.class)
    public Result<?> handleLeaseException(LeaseException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getCode(), e.getMessage());
    }

}
