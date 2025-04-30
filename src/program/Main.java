package program;

import models.cli.CameraMenu;
import models.cli.VideoMenu;
import models.composite.SmartRoom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, SmartRoom> camere = new HashMap<>();

        while (true) {
            System.out.println("\n=== MENIU ===");
            System.out.println("1) Reda videoclip");
            System.out.println("2) Creeaza camera");
            System.out.println("3) Adauga dispozitiv în camera");
            System.out.println("4) Adauga sub-camera într-o camera");
            System.out.println("5) Afiseaza consumul unei camere");
            System.out.println("6) Afiseaza ierarhia unei camere");
            System.out.println("7) Sterge o componenta");
            System.out.println("8) Iesire");
            System.out.print("> ");

            String optiune = sc.nextLine();
            switch (optiune) {
                case "1":
                    VideoMenu.start(sc);
                    break;

                case "2":
                    CameraMenu.createRoom(sc, camere);
                    break;

                case "3":
                    CameraMenu.addDevice(sc, camere);
                    break;

                case "4":
                    CameraMenu.addRoom(sc, camere);
                    break;

                case "5":
                    CameraMenu.showConsumption(sc, camere);
                    break;
                case "6":
                    CameraMenu.showRoomTree(sc, camere);
                    break;

                case "7":
                    CameraMenu.remove(sc, camere);
                    break;

                case "8":
                    System.out.println("La revedere!");
                    exit(0);
                    sc.close();
                    break;

                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}
