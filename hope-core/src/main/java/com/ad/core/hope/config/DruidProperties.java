package com.ad.core.hope.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-05 19:02
 **/
@Configuration
@ConfigurationProperties(prefix = "hope.druid")
@Data
@EqualsAndHashCode(callSuper = false)
@Order(-1)
public class DruidProperties {
    private String username;
    private String password;
    private String servletPath="/druid/*";
    private Boolean resetEnable=false;
    private List<String> allowIps;
    private List<String> denyIps;
}