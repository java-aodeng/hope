package com.hope.demo.factory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-22 15:07
 **/
public class Main {
    public static void main(String[] args) {
        //使用工厂提供类选择工厂
        DrinkStore store=new DrinkStore(new ChinaAbstractDrinksFactory());
        //根据产品生产饮料
        Drink drink=store.createDrink("tea");
        //得到具体工厂的具体饮料
        System.out.println(drink.getName());
    }
}
