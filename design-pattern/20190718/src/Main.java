public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Cook cook = new CookXihongshi2();
        cook.cook();

        Cook cook1=new CookVegetable2();
        cook1.cook();

        /*
===== 控制台输出
Hello World!
打开抽油烟机
生火
西红柿炒蛋
灭火
关闭抽油烟机
打开抽油烟机
生火
炒青菜
灭火
关闭抽油烟机*/
    }
}