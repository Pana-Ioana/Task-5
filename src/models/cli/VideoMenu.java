package models.cli;

import models.classes.VideoPlayer;
import models.proxy.VideoPlayerProxy;

import java.util.Scanner;

public class VideoMenu {
    public static void start(Scanner scanner) {
        System.out.print("Nume fisier video: ");
        String video = scanner.nextLine();
        VideoPlayer videoPlayer = new VideoPlayer(video);
        VideoPlayerProxy proxy = new VideoPlayerProxy(videoPlayer);

        while (true) {
            System.out.println("\n=== MENIU REDARE VIDEO ===");
            System.out.println("1) Reda videoclipul");
            System.out.println("2) Reda videoclipul dupa 5 secunde");
            System.out.println("3) Inapoi la meniul principal");
            System.out.print("> ");

            String optiune = scanner.nextLine();

            switch (optiune) {
                case "1":
                    System.out.println("Rezultat: " + proxy.playVideo());
                    break;

                case "2":
                    try {
                        System.out.println("Asteptam 5 secunde...");
                        Thread.sleep(5000);
                        System.out.println("Rezultat: " + proxy.playVideo());
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    return;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}
