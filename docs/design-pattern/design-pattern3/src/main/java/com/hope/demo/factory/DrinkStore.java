package com.hope.demo.factory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-22 15:03
 * 工厂提供者
 **/
public class DrinkStore {
    AbstractDrinksFactory abstractDrinksFactory;

    /***
     * 动态的选择工厂
     * @param abstractDrinksFactory
     */
    public DrinkStore(AbstractDrinksFactory abstractDrinksFactory){
        this.abstractDrinksFactory=abstractDrinksFactory;
    }

    /***
     * 根据产品类型生产
     * @param whatYouLike
     * @return
     */
    public Drink createDrink(String whatYouLike){
        return abstractDrinksFactory.createDrink(whatYouLike);
    }
}
