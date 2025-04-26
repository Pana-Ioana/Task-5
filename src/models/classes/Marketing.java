package models.classes;

public class Marketing {
    public static void main(String[] args) {
        VideoPlayer video = null;
        try{
            video = new VideoPlayer("Kebap marketing.mp4");
            System.out.println(video.playVideo());
        } catch(NullPointerException e){
            System.out.println("The video is not rendered yet...");
        }


        try {
            Thread.sleep(5000);
            System.out.println("Playing " + video.playVideo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
