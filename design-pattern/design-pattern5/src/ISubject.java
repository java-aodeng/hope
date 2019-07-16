/**
 * 定义被观察者接口
 * @author aodeng-低调小熊猫
 * @since 19-7-15
 */
public interface ISubject {
    public void registerObserver(Customer customer);
    public void removeObserver(Customer customer);
    public void notifyObservers();

}
