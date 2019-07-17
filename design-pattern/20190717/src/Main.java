public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //客户端调用
        MusicPlayer musicPlayer=new PlayerAdapter();
        musicPlayer.play("mp3","XXX.mp3 file");
        musicPlayer.play("mp4","XXX.mp4 file");
        //控制台输出
        /*
        Hello World!
        play mp3XXX.mp3 file
        play mp4XXX.mp4 file
        */
    }
}
