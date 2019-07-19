/**
 * 抽象父类 两个地方是关键，一是 doCook() 使用 abstract 修饰，让子类去实现。二是 cook() 方法使用 final 关键字修饰，防止子类重写，从而破坏了模板中规定好的流程。
 * @author aodeng-低调小熊猫
 * @since 19-7-18
 */
public abstract class Cook {

    public void open(){
        System.out.println("打开抽油烟机");
    }
    public void fire(){
        System.out.println("生火");
    }
    /**
     * 期望子类去实现
     */
    public abstract void doCook();

    public void outfire(){
        System.out.println("灭火");
    }
    public void close(){
        System.out.println("关闭抽油烟机");
    }
    /**
     * 使用final关键字，防止子类重写
     */
    public final void cook(){
        this.open();
        this.fire();
        this.doCook();
        this.outfire();
        this.close();
    }

}
