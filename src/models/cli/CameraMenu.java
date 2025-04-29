package models.cli;

import models.composite.SmartDevice;
import models.composite.SmartRoom;
import models.interfaces.IOTDevice;

import java.util.Map;
import java.util.Scanner;

public class CameraMenu {
    public static void createRoom(Scanner scanner, Map<String, SmartRoom> camere){
        System.out.print("Nume camera: ");
        String camera = scanner.nextLine();

        SmartRoom cameraExistenta = camere.get(camera);

        if (cameraExistenta != null) {
            System.out.println("Camera " + camera + " exista deja!");
        } else {
            camere.put(camera, new SmartRoom(camera));
            System.out.println("Camera " + camera + " a fost creata!");
        }
    }

    public static void addDevice(Scanner scanner, Map<String, SmartRoom> camere){
        System.out.print("Introdu numele camerei: ");
        String camera = scanner.nextLine();

        SmartRoom room = camere.get(camera);

        if (room == null) {
            System.out.println("Camera " + camera + " nu exista. Verifica numele si incearca din nou!");
            return;
        }

        System.out.print("Introdu numele dispozitivului: ");
        String numeDispozitiv = scanner.nextLine();
        System.out.print("Introdu consumul dispozitivului (kWh): ");
        int consum = Integer.parseInt(scanner.nextLine());

        for (IOTDevice d : room.getChildren()) {
            if (d instanceof SmartDevice && ((SmartDevice) d).getName().equals(numeDispozitiv)) {
                int existent = ((SmartDevice) d).getConsumption();
                System.out.println("Dispozitivul " + numeDispozitiv + " exista deja cu consum de " + existent + " kWh!");
                return;
            }
        }

        room.addComponent(new SmartDevice(numeDispozitiv, consum));
        System.out.println("Dispozitivul " + numeDispozitiv + " a fost adaugat cu succes in camera " + camera + "!");
    }

    public static void addRoom(Scanner sc, Map<String, SmartRoom> camere) {
        System.out.print("Introdu numele camerei: ");
        String camera = sc.nextLine();
        System.out.print("Introdu numele sub-camerei: ");
        String child = sc.nextLine();

        SmartRoom cameraParinte = camere.get(camera);
        SmartRoom cameraCopil = camere.get(child);

        if (cameraParinte == null || cameraCopil == null) {
            System.out.println("Nu am gasit una sau ambele camere. Verifica numele introduse si incearca din nou!");
            return;
        }

        if (cameraCopil.containsRoom(cameraParinte)) {
            System.out.println("EROARE: Nu poti adauga camera " + child + " in " + camera + " pentru ca s-ar crea un ciclu!");
            return;
        }

        cameraParinte.addComponent(cameraCopil);
        System.out.println("Sub-camera " + child + " a fost adaugata cu succes in camera " + camera + "!");
    }

    public static void showConsumption(Scanner sc, Map<String, SmartRoom> camere) {
        System.out.print("Introdu numele camerei pentru care vrei sa afli consumul: ");
        String numeCamera = sc.nextLine();

        SmartRoom camera = camere.get(numeCamera);

        if (camera == null) {
            System.out.println("Nu am gasit camera " + numeCamera + ". Verifica numele si incearca din nou!");
            return;
        }

        camera.identify();
    }

    public static void showRoomTree(Scanner sc, Map<String, SmartRoom> camere) {
        System.out.print("Introdu numele camerei pentru care vrei sa afisezi ierarhia: ");
        String numeCamera = sc.nextLine();

        SmartRoom camera = camere.get(numeCamera);

        if (camera == null) {
            System.out.println("Nu am gasit camera " + numeCamera + ". Verifica numele si incearca din nou!");
            return;
        }

        System.out.println("Ierarhia pentru camera " + numeCamera + ":");
        camera.printHierarchy("  ");
    }


}
