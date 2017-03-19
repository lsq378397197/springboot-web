package com.lsq.utils;

import com.lsq.domain.Result;
import org.hibernate.transform.ResultTransformer;

/**
 * Created by Administrator on 2017-3-19.
 */
public class RequestUtil {
    public static Result success(Object obj) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(obj);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
