package com.ad.core.hope.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-08-31 13:46
 **/
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.first")
public class FirstDataProperties {
    String driverClassName;
    String url;
    String username;
    String password;
}
