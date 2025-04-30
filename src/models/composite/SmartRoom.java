package models.composite;

import models.interfaces.IOTDevice;

import java.util.ArrayList;
import java.util.List;

public class SmartRoom implements IOTDevice {
    private final String name;
    private final List<IOTDevice> children = new ArrayList<>();

    public SmartRoom(String name) {
        this.name = name;
    }

    public void addComponent(IOTDevice component) {
        children.add(component);
    }

    public void removeComponent(String name){
        IOTDevice device = null;

        for (IOTDevice c : children) {
            if (c instanceof SmartDevice && ((SmartDevice) c).getName().equals(name)) {
                device = c;
                break;
            }
            if (c instanceof SmartRoom && ((SmartRoom) c).getName().equals(name)) {
                device = c;
                break;
            }
        }

        if (device != null) {
            children.remove(device);
            System.out.println("Componenta " + name + " a fost stearsa din camera " + this.name + "!");
        } else {
            System.out.println("Nu exista nicio componenta!");
        }
    }

    @Override
    public void identify() {
        System.out.println("Camera " + this.name + " consum total: " + this.getConsumption() + " kWh");
        for (IOTDevice c : children) {
            c.identify();
        }
    }

    @Override
    public int getConsumption() {
       int totalConsumption = 0;

       for(IOTDevice child : children){
           totalConsumption += child.getConsumption();
       }
       return totalConsumption;
    }

    public boolean containsRoom(SmartRoom room) {
        if (this == room) {
            return true;
        }
        for (IOTDevice child : children) {
            if (child instanceof SmartRoom) {
                if (((SmartRoom) child).containsRoom(room)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printHierarchy(String indent) {
        System.out.println(indent + "Camera: " + name);
        for (IOTDevice c : children) {
            if (c instanceof SmartRoom) {
                ((SmartRoom) c).printHierarchy(indent + "  ");
            } else if (c instanceof SmartDevice d) {
                System.out.println(indent + "  Dispozitiv: " + d.getName() + " (" + d.getConsumption() + "kWh)");
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<IOTDevice> getChildren() {
        return children;
    }
}
