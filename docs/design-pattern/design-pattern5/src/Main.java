import javax.security.auth.Subject;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        NewsOffice newsOffice=new NewsOffice();
        newsOffice.add(new CustomerA());
        newsOffice.add(new CustomerB());
        newsOffice.newspaperCome();

        System.out.println("加强版========");
        ISubject iSubject=new NewsOfficeNiu();
        Customer customera=new CustomerA();
        iSubject.registerObserver(customera);
        iSubject.removeObserver(customera);
        iSubject.registerObserver(new CustomerB());
        ((NewsOfficeNiu) iSubject).newspaperCome();
    }
}
