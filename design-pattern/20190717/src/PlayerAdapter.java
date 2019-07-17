/**
 * 写个适配器 适配器实现我们的新接口，在适配器中使用我们的旧接口
 * @author aodeng-低调小熊猫
 * @since 19-7-17
 */
public class PlayerAdapter implements MusicPlayer{

    //使用旧接口
    private ExistPlayer existPlayer;

    public PlayerAdapter(){
        existPlayer=new ExistPlayer();
    }

    @Override
    public void play(String type, String fileName) {
        if ("mp3".equals(type)){
            existPlayer.playMp3(fileName);
        }else if ("mp4".equals(type)){
            existPlayer.playMp4(fileName);
        }

    }
}
