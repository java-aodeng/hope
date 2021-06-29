package com.hope.demo.factory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-22 14:57
 * 中国工厂 生产coffer，tea
 **/
public class ChinaAbstractDrinksFactory extends AbstractDrinksFactory {
    @Override
    public Drink createDrink(String whatYouLike) {
        Drink drink=null;
        if ("coffer".equals(whatYouLike)){
            drink=new CofferDrink();
        }else if ("tea".equals(whatYouLike)){
            drink=new TeaDrink();
        }
        return drink;
    }
}
