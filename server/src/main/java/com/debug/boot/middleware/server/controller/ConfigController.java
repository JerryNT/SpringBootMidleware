package com.debug.boot.middleware.server.controller;

import com.debug.boot.middleware.api.response.BaseResponse;
import com.debug.boot.middleware.api.response.StatusCode;
import com.debug.boot.middleware.server.dto.WxAuthTokenDto;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 读取配置文件controller
 */

@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private Environment env;

    //读取配置文件中变量的相关信息
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public BaseResponse info(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> dataMap = Maps.newHashMap();
        try {
            String appId = env.getProperty("wx.auth.token.appId");
            Integer appVersion = env.getProperty("wx.auth.token.appVersion", Integer.class);

            dataMap.put("appId", appId);
            dataMap.put("appVersion", appVersion);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(dataMap);
        return response;
    }

    @Value("${wx.auth.token.appId}")
    private String appId;
    @Value("${wx.auth.token.appVersion}")
    private String appVersion;
    //读取配置文件中变量的相关信息
    @RequestMapping(value = "info/v2", method = RequestMethod.GET)
    public BaseResponse infov2(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> dataMap = Maps.newHashMap();
        try {
            dataMap.put("appId", appId);
            dataMap.put("appVersion", appVersion);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(dataMap);
        return response;
    }

    @Autowired
    private WxAuthTokenDto tokenDto;
    //读取配置文件中变量的相关信息
    @RequestMapping(value = "info/v3", method = RequestMethod.GET)
    public BaseResponse infov3(){
        BaseResponse response = new BaseResponse(StatusCode.Success);
        Map<String,Object> dataMap = Maps.newHashMap();
        try {
            dataMap.put("appId", tokenDto.getAppId());
            dataMap.put("appVersion", tokenDto.getAppVersion());
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(dataMap);
        return response;
    }
}
