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

    @Override
    public void identify() {
        System.out.print("Camera " + this.name + " consum total: " + this.getConsumption() + " kWh");
        for (IOTDevice c : children) {
            c.identify();
        }
    }

    @Override
    public int getConsumption() {
        return children.stream()
                .mapToInt(IOTDevice::getConsumption)
                .sum();
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
            } else if (c instanceof SmartDevice) {
                SmartDevice d = (SmartDevice) c;
                System.out.println(indent + "  Dispozitiv: " + d.getName() +
                        " (" + d.getConsumption() + "kWh)");
            }
        }
    }
}
