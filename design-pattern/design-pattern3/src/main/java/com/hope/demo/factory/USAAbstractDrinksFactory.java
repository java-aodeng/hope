package com.hope.demo.factory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-22 14:56
 * 美国工厂 生产coffer，sodas
 **/
public class USAAbstractDrinksFactory extends AbstractDrinksFactory {
    @Override
    public Drink createDrink(String whatYouLike) {
        Drink drink=null;
        if ("coffer".equals(whatYouLike)){
            drink=new CofferDrink();
        }else if ("sodas".equals(whatYouLike)){
            drink=new SodasDrink();
        }
        return drink;
    }
}
