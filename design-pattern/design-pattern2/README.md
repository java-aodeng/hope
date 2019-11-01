## 单例模式
**单例模式确保一个类只有一个实例，并提供一个全局访问点**

## 使用
**懒汉式**
````
/**
 * @program:hope
 * @author:aodeng
 * @微信公众号:低调小熊猫
 * @create:2018-11-15 13:20
 **/
public class SingleObject {
    /**===============懒汉式(那个取的名字，本人表示根本记不住)英文名，叫 lazy loading，也就是延迟加载===============**/
    private static SingleObject instance;
    /**让构造函数为 private，这样该类就不会被实例化**/
    private SingleObject(){}
    /**但是上面代码会有一个问题，当多个线程同时调用 getInstance() 方法时，可能会产生多个instance 实例，因此这种方式并不是真正的单例。
       为了解决线程安全问题，我们只需要在getInstance() 方法上使用synchronized 关键字给线程加锁即可
       synchronized 的作用是加锁，当多个线程同时调用getInstance() 时，只有一个线程能进入，其他线程会等待进入的线程出来之后在一一进入，
       这样就能保证instance 实例是唯一的。这才是真正的单例，不过这并不是完美的解决方案，只要是锁，必然有性能损耗问题。而且对于上面的代码，
       其实我们只需要在线程第一次访问时加锁即可，之后并不需要锁，锁给我们带来了系统资源浪费**/
    public static synchronized SingleObject getInstance(){
        if (null == instance){
            instance=new SingleObject();
        }
        return instance;
    }

    private String name="低调小熊猫";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
````
**饿汉式**
````
/**
 * @program:hope
 * @author:aodeng
 * @微信公众号:低调小熊猫
 * @create:2018-11-15 13:34
 **/
public class SingleObject2 {
    /**=======饿汉式(低调小熊猫表示还是记不住)新的解决方案是not lazy loading，在类加载时就创建好了实例，解决懒汉式锁给我们带来了系统资源浪费===============**/
    private static SingleObject2 instance=new SingleObject2();
    private SingleObject2(){}
    public static SingleObject2 getInstance(){
        return instance;
    }
    /**这种方式就可以保证实例唯一了**/
    private String name="低调小熊猫2";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
````
**double-checked locking （双重检查加锁）**
````
/**
 * @program:hope
 * @author:aodeng
 * @微信公众号:低调小熊猫
 * @create:2018-11-15 13:45
 **/
public class SingleObject3 {
    /**=======还有一种叫 double-checked locking （双重检查加锁）==============**/
    /**这种方式主要用到两个关键字volatile 和 synchronized，synchronized 的作用是加锁，就不再多说，而volatile 关键字许多人不了解，没关系，我们先看代码**/
    private volatile static SingleObject3 instance;
    private SingleObject3(){}
    private static SingleObject3 getInstance(){
        if (null == instance) {
            synchronized (SingleObject3.class){
                if (null == instance){
                    instance =new SingleObject3();
                }
            }
        }
        return instance;
    }
    /**volatile 关键字简单来说就是可以保证instance变量在被其中一个线程new出来时，其他线程可以立即看到结果并正确的处理它。对volatile 有兴趣的朋友可以自行度娘
     * 这种方式的单例模式可以大大的减少锁所带来的性能损耗**/
    private String name="低调小熊猫3";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
````
**TestMain**
````
/**
 * @program:hope
 * @author:aodeng
 * @微信公众号:低调小熊猫
 * @create:2018-11-15 13:23
 **/
public class TestMain {
    public static void main(String[] args) {
        /**======单例使用========**/
        //懒汉式
        SingleObject singleObject=SingleObject.getInstance();
        //饿汉式
        SingleObject2 singleObject2=SingleObject2.getInstance();
        //双重检查加锁，额，是不是很尴尬，我不知道怎么用这个
        //SingleObject3 singleObject3=SingleObject3.
        System.out.println(singleObject.getName());
        System.out.println(singleObject2.getName());
        /**
         * 优点
         使用单例模式，对象在内存中只有一个实例，并且无需频繁的创建和销毁对象，大大的减少了性能的损耗
         缺点：懒得打字了，可以忽略
         个人喜欢使用饿汉式单例，也就是not lazy loading，没有为什么，就是这种代码少点，嘿嘿
         **/
    }
}

````
