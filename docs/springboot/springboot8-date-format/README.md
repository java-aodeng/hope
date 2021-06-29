## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootshiyi
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 简介
很多时候日期格式输出是这样的
2018-10-09T17:39:07.097
中间有个T，尴尬，是的我们需要去掉这个T
这方法是springboot封装好了的，我们直接使用即可，普通的配置我就不贴了
## 教程
**创建日期config类**
```
@Configuration
public class LocalDateTimeSerializerConfig {
    @org.springframework.beans.factory.annotation.Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
    }
}
```
**实体类，get，set忽略**
```
public class TestEntity {
    private String name;
    private LocalDateTime dateTimes;
}
```
**controller使用**
```
@GetMapping("/test")
    public TestEntity test(){
        TestEntity testEntity=new TestEntity();
        testEntity.setName("admin");
        testEntity.setDateTimes(LocalDateTime.now());
        return testEntity;
    }
```
**成功效果**
```
{"name":"admin","dateTimes":"2018-10-09 17:39:07"}
```

