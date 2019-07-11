package com.nihaojewelry.quartz.learn;

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
