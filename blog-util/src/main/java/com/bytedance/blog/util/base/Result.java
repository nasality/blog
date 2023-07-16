package com.bytedance.blog.util.base;

import com.alibaba.fastjson.JSON;
import com.bytedance.blog.util.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Data
@AllArgsConstructor
public class Result {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    private static final long serivalVersionUID = 1L;
    private Integer code;
    private String message;
    private Object data;

    public static Result ok() {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), null);
    }

    public static Result ok(Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), data);
    }

    public static Result ok(String message, Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static Result error(String message) {
        logger.debug("返回错误：code={}, message={}", ResultEnum.ERROR.getCode(), message);
        return new Result(ResultEnum.ERROR.getCode(), message, null);
    }

    public static Result build(int code, String message) {
        logger.debug("返回结果：code={}, message={}", code, message);
        return new Result(code, message, null);
    }

    public static Result build(ResultEnum resultEnum) {
        logger.debug("返回结果：code={}, message={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}
