package com.debug.boot.middleware.server.exception;

import com.debug.boot.middleware.api.response.BaseResponse;
import com.debug.boot.middleware.api.response.StatusCode;
import com.debug.boot.middleware.server.controller.AbstractController;
import com.google.common.collect.Maps;
import com.mysql.fabric.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常处理
 */

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractController {

//    @ExceptionHandler(value = NotFoundException.class)
//    public String notFoundHandler(Exception e, HttpServletRequest request) {
//        log.info("---异常信息：{}",e.getMessage());
//        request.setAttribute("errorInfo", e.getMessage());
//        return "404";
//    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public BaseResponse notFoundhandler(Exception e, HttpServletRequest request) {
        BaseResponse response = new BaseResponse(StatusCode.Fail);
        Map<String, Object> resMap = Maps.newHashMap();

        resMap.put("requestURI", request.getRequestURI());
        resMap.put("errorInfo", e.getMessage());

        response.setData(resMap);
        return response;

    }

}
