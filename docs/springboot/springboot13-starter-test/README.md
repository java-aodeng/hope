## 版权声明
>* 本文作者：低调小熊猫
* 本文链接：https://aodeng.cc/archives/springbootshi-wu
* 版权声明：本文采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。转载-非商用-非衍生-保持署名，商业转载请联系作者本人qq:2696284032

## 单纯的广告
>个人博客：https://aodeng.cc
微信公众号：低调小熊猫
QQ群：756796932

## 简介
开发一个优秀的系统，单元测试也是必不可少的，Spring Boot 对单元测试也做了一些支持，MockMVC就是之一，可以模拟web端的post，get请求，测试也能得到详细的过程

### 使用方法
**添加依赖**
```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```
编写测试代码
```
@SpringBootTest
public class Springboot13StarterTestApplicationTests {

	private MockMvc mockMvc;
	
	//初始化资源
	@Before
	public void setMockMvc() throws Exception{
		mockMvc= MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	@Test
	public void test() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=低调小熊猫")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
	}
	@Test
	public void test2() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=低调小熊猫")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("低调小熊猫")));

	}
	@Test
	public void contextLoads() {
		System.out.println("低调小熊猫");
	}

}
```
**代码作用**
accept(MediaType.APPLICATION_JSON_UTF8)) 设置编码格式
andDo(print()) //会将请求和相应的过程都打印出来
Matchers.containsString("str")，判断返回的结果集中是否包含“str”这个字符串

### 运行测试
**我们运行第一个test**
```
MockHttpServletRequest:
      HTTP Method = POST
      Request URI = /hello
       Parameters = {name=[低调小熊猫]}
          Headers = {Accept=[application/json;charset=UTF-8]}
             Body = <no character encoding set>
    Session Attrs = {}

Handler:
             Type = com.hope.controller.HelloController
           Method = public java.lang.String com.hope.controller.HelloController.hello(java.lang.String)

Async:
    Async started = false
     Async result = null

Resolved Exception:
             Type = null

ModelAndView:
        View name = null
             View = null
            Model = null

FlashMap:
       Attributes = null

MockHttpServletResponse:
           Status = 200
    Error message = null
          Headers = {Content-Type=[application/json;charset=UTF-8], Content-Length=[21]}
     Content type = application/json;charset=UTF-8
             Body = 你好低调小熊猫
    Forwarded URL = null
   Redirected URL = null
          Cookies = []
```
**当看到“Body = 你好低调小熊猫”，表示成功了，还能看到整个请求详细信息**

**第二个test，会打印我们请求的结果**

**第三个测试，就是普通的测试了**

**以上代码只是spring-boot-starter-test 组件的一部分功能，还有很多好玩的一起学吧**


这只是我个人的学习笔记，非教程


