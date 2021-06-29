package com.hope.designpattern2.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
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
