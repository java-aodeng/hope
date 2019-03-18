## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootshisans
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 简介
发送邮件可以实现，注册验证，忘记密码等功能，还可以打广告，可谓很nice的功能了
## 使用
**添加依赖**
```
<!--mail-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```
**配置文件**
```html
spring:
  mail:
    host: *** #//邮箱服务器地址
    username: ***
    password: ***
    default-encoding: UTF-8
#以谁来发送邮件
mail.fromMail.addr: ***
```
**封装接口**
```java
public interface EmailService {
    void sendTextEmail(String to,String subject,String content);
    void sendHtmlEmali(String to,String subject,String content);
    void sendAttachmentsEmail(String to,String subject,String content,String filePath);
    void sendStaticResourcesEmail(String to,String subject,String content,String rscPath,String rscId);
}
```
**实现接口，这里是关键代码了**
```
/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-11 13:25
 **/
@Service
public class EmaliServiceImpl implements EmailService {

    private static final Logger log= LoggerFactory.getLogger(EmaliServiceImpl.class);
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /***
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendTextEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            log.info("[文本邮件发送成功，当前时间]-[{}]",new Date());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlEmali(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            //发送邮件
            mailSender.send(message);
            log.info("[html邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /***
     * 附件邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            //上传附件
            FileSystemResource resource=new FileSystemResource(new File(filePath));
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,resource);
            //发送邮件
            mailSender.send(message);
            log.info("[附件邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /***
     * 嵌入静态资源邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    @Override
    public void sendStaticResourcesEmail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            //嵌入静态资源
            FileSystemResource resource=new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,resource);

            //发送邮件
            mailSender.send(message);
            log.info("[嵌入静态资源邮件发送成功，当前时间]-[{}]",new Date());
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

}
```
**测试功能**
```
@Test
    public void test1(){
        //for(int i=0;i<=2;i++){
            emailService.sendTextEmail("邮件地址","测试","收到请回复，over！");
        //}
    }
    @Test
    public void test2(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        emailService.sendHtmlEmali("邮件地址","测试",content);
    }
    @Test
    public void test3(){
        emailService.sendAttachmentsEmail("邮件地址","测试","收到请回复，over！","C:\\Users\\ad182\\Pictures\\image3\\a.jpg");
    }
    @Test
    public void test4(){
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\ad182\\Pictures\\image3\\a.jpg";
        emailService.sendStaticResourcesEmail("邮件地址","测试",content,imgPath,rscId);
    }
```
**还有一个发送邮件验证的功能**
**添加引擎模板**
```
<!-- 模板引擎 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```
**创建一个页面，注意：页面名称和后台参数名称要一致**
```
<body>
        您好,这是验证邮件,请点击下面的链接完成验证,<br/>
        <a href="#" th:href="@{ https://aodeng.cc/{id}(id=${id}) }">激活账号</a>
    </body>
```
**发送验证邮件，其实就是后台拼接一个参数到页面，将页面发个用户，用户点击页面的带参数的链接，后台接收参数，然后就实现验证的功能了**
```
    @Test
    public void test5() {
        //创建邮件正文
        org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        emailService.sendHtmlEmali("f12974049826@163.com","主题：这是模板邮件",emailContent);
    }
```
