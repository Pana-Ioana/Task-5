package models.classes;

import models.interfaces.IVideoPlayer;

import java.util.Random;

public class VideoPlayer implements IVideoPlayer {

    private String fileName = "RickRoll.mkv";
    private static final int MAX_WAIT = 10;
    private static final int MIN_WAIT = 1;
    private static final long MULTIPLIER_WAIT = 1000;

    public VideoPlayer(String name){
        new Thread(() -> {
            try {
                System.out.println("Starting video render...");
                Thread.sleep( (new Random().nextInt(MAX_WAIT - MIN_WAIT + 1)+ MIN_WAIT) * MULTIPLIER_WAIT);
                this.fileName = name;
                System.out.printf("Video render is done for '%s'...\n", this.fileName);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public String playVideo(){
        if(fileName.equals("RickRoll.mkv")){
            throw new NullPointerException();
        }
        else return fileName;
    }

}
