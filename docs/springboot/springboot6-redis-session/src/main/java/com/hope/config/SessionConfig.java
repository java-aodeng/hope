package com.hope.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-09 12:06
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1)
public class SessionConfig {
}
