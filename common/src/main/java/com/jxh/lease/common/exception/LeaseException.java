package com.jxh.lease.common.exception;

import com.jxh.lease.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ClassName: LeaseException
 * Package: com.jxh.lease.common.exception
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/7 20:17
 */
@Data
public class LeaseException extends RuntimeException {

    private Integer code;

    public LeaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        this(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

}
