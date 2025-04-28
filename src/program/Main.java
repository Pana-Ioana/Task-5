package program;

import models.classes.VideoPlayer;
import models.cli.CameraManager;
import models.cli.VideoMenu;
import models.composite.SmartDevice;
import models.composite.SmartRoom;
import models.interfaces.IVideoPlayer;
import models.proxy.VideoPlayerProxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, SmartRoom> camere = new HashMap<>();

        while (true) {
            System.out.println("\n=== MENIU ===");
            System.out.println("1) Redă videoclip");
            System.out.println("2) Creează cameră");
            System.out.println("3) Adaugă dispozitiv în cameră");
            System.out.println("4) Adaugă sub-cameră într-o cameră");
            System.out.println("5) Afișează consumul unei camere");
            System.out.println("6) Afișează ierarhia unei camere");
            System.out.println("7) Ieșire");
            System.out.print("> ");

            String optiune = sc.nextLine();
            switch (optiune) {
                case "1":
                    VideoMenu.start(sc);
                    break;

                case "2":
                    CameraManager.createRoom(sc, camere);
                    break;

                case "3":
                    CameraManager.addDevice(sc, camere);
                    break;

                case "4":
                    CameraManager.addRoom(sc, camere);
                    break;

                case "5":
                    CameraManager.showConsumption(sc, camere);
                    break;
                case "6":
                    CameraManager.showRoomTree(sc, camere);
                    break;

                case "7":
                    System.out.println("La revedere!");
                    sc.close();
                    break;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}
