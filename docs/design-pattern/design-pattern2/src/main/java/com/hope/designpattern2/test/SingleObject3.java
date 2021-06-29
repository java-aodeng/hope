package com.hope.designpattern2.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
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
