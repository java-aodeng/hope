## monitor监控使用-（日志不生效避坑）
### monitor端配置
###### 账号密码
```
# spring
spring: 
  security:
    user:
      name: 登录账号
      password: 登录密码
```

### 监控的服务端配置
###### （避坑）logging必须配置在bootstrap这样的根文件里，不然不生效
```
# 监控
management:
  endpoints:
    web:
      exposure:
        include: "*"
logging:
  file:
    name: logs/${spring.application.name}/info.log
```
