package models.composite;

import models.interfaces.IOTDevice;

public class SmartDevice implements IOTDevice {

    private final String name;
    private final int consumption;

    public SmartDevice(String name, int consumption) {
        this.name = name;
        this.consumption = consumption;
    }

    @Override
    public void identify() {
        System.out.print("\nDispozitivul " + this.name + " consuma " + this.consumption + " kWh");
    }

    @Override
    public int getConsumption() {
        return consumption;
    }

    public String getName() {
        return name;
    }
}
