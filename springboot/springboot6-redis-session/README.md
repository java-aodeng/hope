## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootjiu
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 配置
**pom.xml**
````
<!--redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
````
**application.yml**
```
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring:
  redis:
      database: 0
  # Redis服务器地址
      host: localhost
# Redis服务器连接端口
      port: 6379
# Redis服务器连接密码（默认为空）
      password:
# 连接超时时间（毫秒）
      #timeout: 0
  # 连接池最大连接数（使用负值表示没有限制）
  pool:
    max-active: 8
  # 连接池最大阻塞等待时间（使用负值表示没有限制）
    max-wait: -1
  # 连接池中的最大空闲连接
    max-idle: 8
  # 连接池中的最小空闲连接
    min-idle: 0
```
**RedisConfig.java**
```
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object object : params) {
                    sb.append(object.toString());
                }
                return sb.toString();
            }
        };
    }

    /***
     * springboot 2.0
     * @param connectionFactory
     * @return
     */
    @Bean
    CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        //user信息缓存配置
        RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(30)).disableCachingNullValues().prefixKeysWith("role");
        //product信息缓存配置
        RedisCacheConfiguration productCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)).disableCachingNullValues().prefixKeysWith("test");
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisCacheConfigurationMap.put("role", userCacheConfiguration);
        redisCacheConfigurationMap.put("test", productCacheConfiguration);
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);


        //设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
        //ClassLoader loader = this.getClass().getClassLoader();
        //JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
        //RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
        //RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);


        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //设置默认超过期时间是30秒
        defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
        return cacheManager;
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
```
**SessionConfig.java**
```
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1)
public class SessionConfig {
}
```
## 使用缓存
```
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisTest {
    private static final Logger log= LoggerFactory.getLogger(TestRedisTest.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test//StringRedisTemplate使用的是 StringRedisSerializer
    public void test1() throws Exception{
        stringRedisTemplate.opsForValue().set("name","admin");
        Assert.assertEquals("admin",stringRedisTemplate.opsForValue().get("name"));
        log.info("[redis测试]-[{}]",stringRedisTemplate.opsForValue().get("name"));
    }
    @Test//RedisTemplate使用的是 JdkSerializationRedisSerializer
    public void test2() throws Exception{
        SysRole sysRole=new SysRole();
        sysRole.setRoleId(1);
        sysRole.setRole("1001");
        sysRole.setRole("管理员");
        sysRole.setDescription("这是一个测试管理员");
        ValueOperations<String,SysRole> valueOperations=redisTemplate.opsForValue();
        valueOperations.set("sysRole1",sysRole);
        valueOperations.set("sysRole2",sysRole,2, TimeUnit.SECONDS);
        Thread.sleep(1000);
        log.info("[sysRole2]-[{}]",valueOperations.get("sysRole2"));
        boolean exists=redisTemplate.hasKey("sysRole2");
        if(exists){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
        Assert.assertEquals("管理员",valueOperations.get("sysRole1").getRole());
    }
}
```
**session共享demo**

```
@RestController
public class TestRedis {

    @RequestMapping("/test")
    String test(HttpSession httpSession){
        UUID uuid=(UUID) httpSession.getAttribute("uuid");
        if(null==uuid){
            uuid=UUID.randomUUID();
        }
        httpSession.setAttribute("uuid",uuid);
        return httpSession.getId();
    }
}
```
**redis客户端查看session**
```
keys '*sessions*'
```