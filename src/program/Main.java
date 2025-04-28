package program;

import models.classes.VideoPlayer;
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
            System.out.println("6) Ieșire");
            System.out.print("> ");

            String optiune = sc.nextLine();
            switch (optiune) {
                case "1":
                    System.out.print("Nume fișier video: ");
                    String fisier = sc.nextLine();
                    IVideoPlayer realPlayer = new VideoPlayer(fisier);
                    IVideoPlayer proxy = new VideoPlayerProxy(realPlayer);
                    System.out.println("Rezultat redare: " + proxy.playVideo());
                    break;

                case "2":
                    System.out.print("Nume cameră: ");
                    String numeCamera = sc.nextLine();
                    if (camere.containsKey(numeCamera)) {
                        System.out.println("Camera „" + numeCamera + "” există deja.");
                    } else {
                        camere.put(numeCamera, new SmartRoom(numeCamera));
                        System.out.println("Camera „" + numeCamera + "” a fost creată.");
                    }
                    break;

                case "3":
                    System.out.print("Nume cameră: ");
                    String cam = sc.nextLine();
                    SmartRoom camera1 = camere.get(cam);
                    if (camera1 == null) {
                        System.out.println("Nu există camera „" + cam + "”.");
                        break;
                    }
                    System.out.print("Nume dispozitiv: ");
                    String numeDispozitiv = sc.nextLine();
                    System.out.print("Consum (kWh): ");
                    int consum = Integer.parseInt(sc.nextLine());
                    camera1.addComponent(new SmartDevice(numeDispozitiv, consum));
                    System.out.println("Dispozitivul „" + numeDispozitiv + "” a fost adăugat în camera „" + cam + "”.");
                    break;

                case "4":
                    System.out.print("Nume cameră părinte: ");
                    String parinte = sc.nextLine();
                    System.out.print("Nume sub-cameră: ");
                    String copil = sc.nextLine();
                    SmartRoom cameraParinte = camere.get(parinte);
                    SmartRoom cameraCopil   = camere.get(copil);
                    if (cameraParinte == null || cameraCopil == null) {
                        System.out.println("Una sau ambele camere nu există.");
                    } else {
                        cameraParinte.addComponent(cameraCopil);
                        System.out.println("Sub-camera „" + copil + "” a fost adăugată în camera „" + parinte + "”.");
                    }
                    break;

                case "5":
                    System.out.print("Nume cameră: ");
                    String c5 = sc.nextLine();
                    SmartRoom camera2 = camere.get(c5);
                    if (camera2 == null) {
                        System.out.println("Nu există camera „" + c5 + "”.");
                    } else {
                        camera2.identify();
                    }
                    break;

                case "6":
                    System.out.println("La revedere!");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
    }
}
