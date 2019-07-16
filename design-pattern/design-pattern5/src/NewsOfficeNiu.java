import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**被观察者 报社 加强版
 * @author aodeng-低调小熊猫
 * @since 19-7-15
 */
public class NewsOfficeNiu implements ISubject {

    private List<Customer> customerList = new ArrayList<>();

    @Override
    public void registerObserver(Customer customer) {
        this.customerList.add(customer);
    }

    @Override
    public void removeObserver(Customer customer) {
        customerList.remove(customer);
    }

    @Override
    public void notifyObservers() {
        for (Customer customer : customerList) {
            customer.update();
        }
    }

    //模拟报纸来了
    public void newspaperCome(){
        this.notifyObservers();
    }
}
