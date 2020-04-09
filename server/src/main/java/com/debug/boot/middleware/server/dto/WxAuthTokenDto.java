package com.debug.boot.middleware.server.dto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@ConfigurationProperties(prefix = "wx.auth.token")
@Configuration
@Data
public class WxAuthTokenDto implements Serializable {
    private String appId;
    private Integer appVersion;
}
