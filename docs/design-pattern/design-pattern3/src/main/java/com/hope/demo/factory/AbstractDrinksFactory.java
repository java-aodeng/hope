package com.hope.demo.factory;

/**
 * @program:hope
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2018-11-22 14:43
 * 抽象工厂
 **/
public abstract class AbstractDrinksFactory {
    /***
     *
     * @param whatYouLike 你喜欢的饮料类型：coffer，tea，sodas...
     * @return
     */
    public abstract Drink createDrink(String whatYouLike);
}
