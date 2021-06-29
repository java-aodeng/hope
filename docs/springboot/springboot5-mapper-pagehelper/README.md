## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootba
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 整合demo
## 配置依赖
```
<!--通用mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.0.2</version>
        </dependency>
        <!--分页插件-->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>1.2.5</version>
        </dependency>
        <!-- MYSQL包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```
**配置静态资源文件**
```
<resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
```
**配置application.yml文件**
```
spring:
  datasource:
    url: 
    password:
    username: 
# 如果想看到mybatis日志需要做如下配置
logging:
  level:
    com.hope: DEBUG
########## Mybatis 自身配置 ##########
mybatis:
  mapper-locations: classpath:/mapper/*.xml
  type-aliases-package: com.hope.model
# 驼峰命名规范 如：数据库字段是  order_id 那么 实体字段就要写成 orderId
#mybatis.configuration.map-underscore-to-camel-case=true
########## 通用Mapper ##########
# 主键自增回写方法,默认值MYSQL,详细说明请看文档
mapper:
  identity: MYSQL

# 设置 insert 和 update 中，是否判断字符串类型!=''
  not-empty: true
# 枚举按简单类型处理
  enum-as-simple-type: true
########## 分页插件 ##########
pagehelper:
  helper-dialect: mysql
  params: count=countSql
  reasonable: false
  support-methods-arguments: true
```
**然后自己创建model，mapper，xml，service**
## 通用mapper的使用
```
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole>{
}
```
## junitTest
**idea快捷键生成junitTest**

**打开要测试的类，如果选择idea工具栏的Navigate，然后点击Test就行了**
```
RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImplTest.class);

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @Test
    public void test1(){
        List<SysRole> sysRoleList=sysRoleService.SelectAll();
        log.info("[普通写法] - [{}]", sysRoleList);
        //分页
        PageInfo<Object> pageInfo= PageHelper.startPage(1,1).doSelectPageInfo(() -> sysRoleService.SelectAll());
        log.info("[分页]-[{}]",pageInfo);
    }
}
```
## Logger
**打印比较详细**
```
 private static final Logger log = LoggerFactory.getLogger(SysRoleServiceImplTest.class);
log.info("[分页]-[{}]",pageInfo);
```
## lambda
```
PageInfo<Object> pageInfo= PageHelper.startPage(1,1).doSelectPageInfo(() -> sysRoleService.SelectAll());
```