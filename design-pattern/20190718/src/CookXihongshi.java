/**
 * 炒个西红柿
 * @author aodeng-低调小熊猫
 * @since 19-7-18
 */
public class CookXihongshi {

    public void open(){
        System.out.println("打开抽油烟机");
    }
    public void fire(){
        System.out.println("生火");
    }
    public void doCook(){
        System.out.println("西红柿炒蛋");
    }
    public void outfire(){
        System.out.println("灭火");
    }
    public void close(){
        System.out.println("关闭抽油烟机");
    }
    public void cook(){
        this.open();
        this.fire();
        this.doCook();
        this.outfire();
        this.close();
    }
}
