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

        Integer consumExistent = null;
        for (SmartRoom r : camere.values()) {
            for (IOTDevice d : r.getChildren()) {
                if (d instanceof SmartDevice sd && sd.getName().equals(numeDispozitiv)) {
                    consumExistent = sd.getConsumption();
                    break;
                }
            }
            if (consumExistent != null) {
                break;
            }
        }

        int consum;
        if (consumExistent != null) {
            consum = consumExistent;
            System.out.println("Dispozitivul „" + numeDispozitiv +
                    "” există deja în altă cameră cu consumul de " +
                    consum + " kWh. Folosim același consum.");
        } else {
            System.out.print("Introdu consumul dispozitivului (kWh): ");
            consum = Integer.parseInt(scanner.nextLine());
        }
        room.addComponent(new SmartDevice(numeDispozitiv, consum));
        System.out.println("Dispozitivul „" + numeDispozitiv +
                "” a fost adăugat în camera „" + camera +
                "” cu consum " + consum + " kWh.");
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
