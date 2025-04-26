package models.composite;

import models.interfaces.IOTDevice;

public class SmartDevice implements IOTDevice {

    private String name;
    private int consumption;

    public SmartDevice(String name, int consumption) {
        this.name = name;
        this.consumption = consumption;
    }

    @Override
    public void identify() {

    }

    @Override
    public int getConsumption() {
        return 0;
    }
}
