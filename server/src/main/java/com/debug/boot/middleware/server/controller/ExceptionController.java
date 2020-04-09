package com.debug.boot.middleware.server.controller;

import com.debug.boot.middleware.api.response.BaseResponse;
import com.debug.boot.middleware.api.response.StatusCode;
import com.debug.boot.middleware.model.entity.UserOrder;
import com.debug.boot.middleware.server.exception.NotFoundException;
import com.debug.boot.middleware.server.service.UserOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("exception")
public class ExceptionController extends AbstractController{

    @Autowired
    private UserOrderService userOrderService;

    @RequestMapping(value = "info",method = RequestMethod.GET)
    public BaseResponse info(@RequestParam String orderNo) throws NotFoundException, Exception {
        if (StringUtils.isBlank(orderNo)) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        UserOrder entity = userOrderService.getInfo(orderNo);
        if (entity == null) {
            throw new NotFoundException("当前没有该订单的详情");
        }
        BaseResponse response=new BaseResponse(StatusCode.Success);
        response.setData(entity);
        return response;
    }
}

