package models.proxy;

import models.interfaces.IVideoPlayer;

public class VideoPlayerProxy implements IVideoPlayer {

    IVideoPlayer videoPlayer;

    public VideoPlayerProxy(IVideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    @Override
    public String playVideo() {
        try{
            return videoPlayer.playVideo();
        } catch (NullPointerException ex){
            return "Incearca mai tarziu...";
        }
    }
}
