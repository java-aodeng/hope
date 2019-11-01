## 抽象工厂模式
**本来不想写这篇文章的，应该是不想写工厂模式，因为基本大家都会，不过今天看到一个老铁的博客，https://www.yuxuan66.com/16 关于抽象模式的实现，写得不错，看了几遍之后，有了点自己的想法，随便再熟悉一下工厂模式，于是就有了这篇文章，主要是抽象工厂，引用下老铁的例子：咖啡工厂做大做强，引入了新的饮品种类：茶、碳酸饮料。中国工厂仅能制作咖啡和茶，美国工厂仅能制作咖啡和碳酸饮料**

## 使用
**我这里创建一个抽象工厂，中国工厂和美国工厂继承它。再创建一个饮料接口，实现各种饮料种类。再创建一个提供者，根据具工厂和种类提供对应的产品**

**创建一个抽象工厂**
```
/**
 * @program:hope
 * @author:aodeng
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
```
**中国工厂和美国工厂继承它**
```
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
//
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
```
**再创建一个饮料接口**
```
//饮料接口
public interface Drink {
    String getName();
}
```
**实现各种饮料种类**
```
//咖啡饮料
public class CofferDrink implements Drink {
    @Override
    public String getName() {
        return "coffer";
    }
}
//碳酸饮料
public class SodasDrink implements Drink {
    @Override
    public String getName() {
        return "sodas";
    }
}
 // 茶饮料
public class TeaDrink implements Drink {
    @Override
    public String getName() {
        return "tea";
    }
}
```
**再创建一个提供者，更具工厂和种类提供对应的产品**
```
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
```
**Main方法测试**
```
/**
 * @program:hope
 * @author:aodeng
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
        System.out.println(drink.getName());//输出tea
    }
}
```

**简单工厂和抽象工厂有些区别，除了结构上的区别，主要区别在于使用场景不同。**

**简单工厂用于创建单一产品，将所有子类的创建过程集中在一个工厂中，如要修改，只需修改一个工厂即可。简单工厂经常和单例模式一起使用，例如用简单工厂创建缓存对象（文件缓存），某天需要改用redis缓存，修改工厂即可。
抽象工厂常用于创建一整个产品族，而不是单一产品。通过选择不同的工厂来达到目的，其优势在于可以通过替换工厂而快速替换整个产品族。例如上面的例子美国工厂生产美国drink，中国工厂生产中国drink。
优点
客户端与具体要创建的产品解耦，扩展性和灵活性高**

**缺点
增加要创建的对象时，需要增加的代码较多，会使系统变得较为复杂**
