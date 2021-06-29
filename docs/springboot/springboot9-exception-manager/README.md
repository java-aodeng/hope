## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootshiers
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 简介
通常在Controller层需要去捕获service层的异常，防止返回一些不友好的错误信息到客户端，但如果Controller层每个方法都用模块化的try-catch代码去捕获异常，会很难看也难维护，所以使用全局异常比较方便
这方法是springboot封装好了的，我们直接使用即可，普通的配置我就不贴了
## 使用
**配置类**
```
/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-10 14:15
 **/
@RestControllerAdvice //该注解将异常以json格式输出
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    /***
     *定义要普获的异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ExceptionEntity customExceptionEntity(HttpServletRequest request, final Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException customException=(CustomException)e;
        return new ExceptionEntity(customException.getCode(),customException.getMessage());
    }

    /***
     * 捕获  RuntimeException 异常
     * 如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     * 那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ExceptionEntity runtimeExceptionEntity(HttpServletRequest request,final Exception e,HttpServletResponse response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtimeException=(RuntimeException)e;
        return new ExceptionEntity(400,runtimeException.getMessage());
    }

    /***
     * Override ResponseEntityExceptionHandler类中的handleExceptionInternal方法
     * 通用的接口映射异常处理方
     * @param e
     * @param o
     * @param httpHeaders
     * @param httpStatus
     * @param webRequest
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object o, HttpHeaders httpHeaders,
                                                    HttpStatus httpStatus, WebRequest webRequest){
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException methodArgumentNotValidException=(MethodArgumentNotValidException)e;
            return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),methodArgumentNotValidException.getBindingResult().getAllErrors().get(0).getDefaultMessage()),httpStatus);
        }
        if(e instanceof MethodArgumentTypeMismatchException){
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException=(MethodArgumentTypeMismatchException)e;
            logger.error("参数转换失败，方法："+methodArgumentTypeMismatchException.getParameter().getMethod().getName()+",参数："+
            methodArgumentTypeMismatchException.getName()+"，信息："+methodArgumentTypeMismatchException.getLocalizedMessage());
            return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),"参数转换失败"),httpStatus);
        }
        return new ResponseEntity<>(new ExceptionEntity(httpStatus.value(),"参数转换失败"),httpStatus);
    }
}
```
**创建CustomException 继承 RuntimeException类**
```
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -8400239090334588012L;
    private int code;

    public CustomException(){
        super();
    }

    public CustomException(int code,String message){
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
```
**创建ErrorResponseEntity实体类，用存放异常信息**
```
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -8400239090334588012L;
    private int code;

    public CustomException(){
        super();
    }
	//忽略get，set
}
```
**测试**
```
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(Integer number){
        if (null == number){
            throw new CustomException(110,"number不能为空，其实是400");
        }
        int i=100/ number;
        return "你输入的数字缩小100倍是："+i;
    }
}
```