/**
 * 收到通知的观察者客户A
 * @author aodeng-低调小熊猫
 * @since 19-7-15
 */
public class CustomerA extends Customer {
    @Override
    public void update() {
        System.out.println("我是客户A，我收到报纸了！");
    }
}
