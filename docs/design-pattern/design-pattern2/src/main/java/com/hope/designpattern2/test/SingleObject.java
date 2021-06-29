package com.hope.designpattern2.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
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
