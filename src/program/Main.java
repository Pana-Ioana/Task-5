package program;

import models.classes.VideoPlayer;
import models.proxy.VideoPlayerProxy;

public class Main {
    public static void main(String[] args) {

        System.out.println("-------Prima incercare de redare pentru ambele videoclipuri-------");

        VideoPlayer videoPlayer1 = new VideoPlayer("Noul Kebap Fara Lipie.mp4");
        VideoPlayerProxy proxy1 = new VideoPlayerProxy(videoPlayer1);

        VideoPlayer videoPlayer2 = new VideoPlayer("Familia Contralipielescu.mp4");
        VideoPlayerProxy proxy2 = new VideoPlayerProxy(videoPlayer2);


        System.out.println("Video 1: " + proxy1.playVideo());
        System.out.println("Video 2: " + proxy2.playVideo());

        System.out.println("\n-------Asteptam 5 secunde. Incercam din nou-------");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Video 1: " + proxy1.playVideo());
        System.out.println("Video 2: " + proxy2.playVideo());


    }
}