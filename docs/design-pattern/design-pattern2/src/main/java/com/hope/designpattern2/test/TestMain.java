package com.hope.designpattern2.test;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
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
