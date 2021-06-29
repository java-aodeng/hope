## 策略模式

**这里我举一个计算器的例子**

**我会创建一个计算接口，接口实现加法，减法这两个类，再创建一个计算器类（设置计算方法，返回值），最后创建一个test类测试**
计算接口
````
public interface Operation {
    public int doOperation(int number1,int number2);
}
````
接口实现加法，减法这两个类
````
public class OperationAdd implements Operation {
    @Override
    public int doOperation(int number1, int number2) {
        return number1+number2;
    }
}

public class OperationSub implements Operation {
    @Override
    public int doOperation(int number1, int number2) {
        return number1-number2;
    }
}
````
再创建一个计算器类（设置计算方法，返回值）
````
public class Cal {
    private Operation operation;

    public void setOperation(Operation operation){
        this.operation=operation;
    }
    public int doOperation(int number1,int number2){
        return this.operation.doOperation(number1,number2);
    }
}
````
最后创建一个test类测试
````
/**
 * @program:hope
 * @author:aodeng
 * @微信公众号:低调小熊猫
 * @create:2018-11-08 20:40
 **/
public class TestController {
    private static final Logger log= LoggerFactory.getLogger(TestController.class);
    public static void main(String[] args) {
        Cal cal=new Cal();
        //使用加法
        cal.setOperation(new OperationAdd());
        //输出结果
        log.info("[计算器加]-[{}]",cal.doOperation(4,2));

        //使用减法
        cal.setOperation(new OperationSub());
        //输出结果
        log.info("[计算器减]-[{}]",cal.doOperation(4,2));
    }
}
````
运行效果
````
"C:\Program Files\Java\jdk1.8\bin\java" com.hope.test.TestController
20:44:33.994 [main] INFO com.hope.test.TestController - [计算器加]-[6]
20:44:34.012 [main] INFO com.hope.test.TestController - [计算器减]-[2]

Process finished with exit code 0
````

**设计模式的基本原则：开闭原则。
意思就是：对修改关闭、对扩展开放**

我们的计算器算和接口算是修改关闭的了，如果要扩展其他算法，乘，除，只需要实现计算接口，然后调用计算器的setOption(new 扩展的接口)就行了，这样我们程序，扩展性就非常强大了。

以下是我看教程的时候的一段话，我直接copy过来一下(哥认为最后一句话是重点，圈起来)：
优点
已经十分明显了，那就是遵循了开闭原则，扩展性良好。

缺点
随着你的策略增加，你的类也会越来越多。
所有的策略类都要暴露出去，所以如果你在实际开发中使用了策略模式，一定要记得写好文档让你的伙伴们知道已有哪些策略。就像 shiro 默认提供了三种验证策略，就必须在文档中写清楚，否则我们根本不知道如何使用。

当然，权衡利弊，跟优点比起来，这些缺点都不算事儿。



