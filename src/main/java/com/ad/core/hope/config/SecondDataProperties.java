package com.ad.core.hope.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-31 13:47
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.second")
public class SecondDataProperties {
    String driverClassName;
    String url;
    String username;
    String password;
}
