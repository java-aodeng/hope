## 装饰器模式
装饰器模式中主要有两个角色：
- 装饰器（夹克，帽子）
- 被装饰的对象（老王）

装饰器和被装饰的对象有两个特点，也是装饰器模式的关键：

- 他们实现同一个接口
- 装饰器中使用了被装饰的对象

## 使用：
```
/**
 * <p>
 *定义一个接口
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public interface Person {
    /**
     * 计算累计消费
     * @return
     */
    public Double cost();

    /**
     * 输出信息
     */
    public void show();
}
```

```
/**
 * <p>
 *老王（ 被装饰的对象 ）实现定义的接口
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public class LaoWang implements Person{
    @Override
    public Double cost() {
        //没消费，消费为0
        return 0.0;
    }

    @Override
    public void show() {
        System.out.println("老王我现在消费"+this.cost());
    }
}
```

```
/**
 * <p>
 *装饰器超类，和被装饰的对象实现同一个接口 Person ：
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public abstract class ClothesDecorator implements Person{
    /**
     * 装饰器中要使用被装饰器的对象，构造方法中传入
     */
    protected Person person;

    public ClothesDecorator(Person person){
        this.person=person;
    }
}
```

```
/**
 * <p>
 *具体的装饰，夹克
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public class Jacket extends ClothesDecorator{

    public Jacket(Person person){
        /**
         * super 指向自己超（父）类
         */
        super(person);
    }
    @Override
    public Double cost() {
        //消费一件夹克
        return person.cost()+100;
    }

    @Override
    public void show() {
        person.show();
        System.out.println("老王我现在又消费了一件夹克100，累计用了"+this.cost());
    }
}
```

```
/**
 * <p>
 *具体的装饰，帽子
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public class Hat extends ClothesDecorator{

    public Hat(Person person){
        //指向自己超（父）类
        super(person);
    }

    @Override
    public Double cost() {
        return person.cost()+200;
    }

    @Override
    public void show() {
        person.show();
        System.out.println("老王我现在又消费了一件帽子200，累计用了"+this.cost());
    }
}
```
## 测试

```
/**
 * <p>
 *程序入口
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public class LearnMain {
    public static void main(String[] args) {
        //创建一个老王
        Person laoWang=new LaoWang();
        //老王买了一件夹克
        laoWang=new Jacket(laoWang);
        //老王买了一个帽子
        laoWang=new Hat(laoWang);
        laoWang.show();
/*
        控制台输出：
        老王我现在消费0.0
        老王我现在又消费了一件夹克100，累计用了100.0
        老王我现在又消费了一件帽子200，累计用了300.0*/
    }
}
```
## 源码

源码地址：https://github.com/java-aodeng/hope

