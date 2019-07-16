import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者 报社
 * @author aodeng-低调小熊猫
 * @since 19-7-15
 */
public class NewsOffice {
    private List<Customer> customerList=new ArrayList<>();

    public void add(Customer customer){
        this.customerList.add(customer);
    }

    /** 
    * <p>
    * 模拟新报纸来了
    * </p> 
    * @author aodeng
    * @since 19-7-15
    */
    public void newspaperCome(){
        this.notifyAllObservers();
    }

    public void notifyAllObservers(){
        for (Customer customer : customerList) {
            customer.update();
        }
    }
}
