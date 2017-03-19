package com.lsq.handler;

import com.lsq.domain.Girl;
import com.lsq.domain.Result;
import com.lsq.exception.GirlException;
import com.lsq.utils.RequestUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-3-19.
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return RequestUtil.fail(girlException.getCode(), girlException.getMessage());
        } else {
            return RequestUtil.fail(-1, e.getMessage());
        }
    }
}
