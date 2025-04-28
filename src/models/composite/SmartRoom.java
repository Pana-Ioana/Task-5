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
        System.out.printf("Camera «%s» consum total: %dkWh%n", name, getConsumption());
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
}
