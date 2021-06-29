package com.nihaojewelry.quartz.learn;

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
