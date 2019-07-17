/**
 * 模拟已经旧的接口 模拟该库是个 jar 包，不能改它源码 的情况下，适配器模式 实现我们的接口
 * @author aodeng-低调小熊猫
 * @since 19-7-17
 */
public class ExistPlayer {

    /**
    * <p>
    * 播放mp3文件
    * </p>
    * @author aodeng
    * @since 19-7-17
    */
    public void playMp3(String fileName){
        System.out.println("play mp3"+fileName);
    }

    /**
    * <p>
    * 播放mp4文件
    * </p>
    * @author aodeng
    * @since 19-7-17
    */
    public void playMp4(String fileName){
        System.out.println("play mp4"+fileName);
    }
}
