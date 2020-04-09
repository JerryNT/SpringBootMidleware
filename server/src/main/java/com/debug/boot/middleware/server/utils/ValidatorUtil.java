package com.debug.boot.middleware.server.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * 请求参数的统一校验器
 */
public class ValidatorUtil {

    public static String checkResult(BindingResult result) {
        StringBuffer sb = new StringBuffer("");
        if (result != null && result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                sb.append(error.getDefaultMessage() + "\n");
            }
        }

        return sb.toString();
    }
}

