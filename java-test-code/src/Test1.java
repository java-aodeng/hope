/**
 * Test1
 *  双重校验锁实现对象单例（线程安全）
 * @author 低调小熊猫(aodeng)
 * @date 2020-07-30
 */
public class Test1 {
    //唯一实例
    private volatile static Test1 uniqueInstance;

    private Test1(){
    }

    public static Test1 getUniqueInstance(){
        //先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (uniqueInstance==null){
            //类对象加锁
            synchronized (Test1.class){
                if (uniqueInstance==null){
                    uniqueInstance=new Test1();
                }
            }
        }
        return uniqueInstance;
    }
}
