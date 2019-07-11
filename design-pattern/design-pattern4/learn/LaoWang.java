package com.nihaojewelry.quartz.learn;

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
