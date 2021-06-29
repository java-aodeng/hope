package com.nihaojewelry.quartz.learn;

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
