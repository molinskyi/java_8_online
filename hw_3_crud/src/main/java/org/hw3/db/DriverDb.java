package org.hw3.db;

import org.hw3.entity.Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DriverDb {

    private Driver[] drivers = new Driver[1]; // чого так мало?)
    private int lastDriverIndex = 0;

    public void create(Driver driver) {
        if (lastDriverIndex == drivers.length - 1) {
            Driver[] newDrivers = new Driver[drivers.length * 2];
            System.arraycopy(drivers, 0, newDrivers, 0, drivers.length);
            drivers = newDrivers;
            add(driver);
        } else {
            add(driver);
        }
    }

    public void add(Driver driver) {
        driver.setId(String.valueOf(lastDriverIndex + 1));
        drivers[lastDriverIndex] = driver;
        lastDriverIndex++;
    }


    public Driver[] findAll() {
        return drivers;
    }

    public Driver findOne(String id) throws IOException {
        int k = 0;
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null) {
                if (drivers[i].getId().equals(id)) {
                    k = i;
                }
            }
        }
        return drivers[k];
    }

    public Driver update(String id) throws IOException {
        // це потрібно робити в контроллері
        // сюди має заходити вже готовий дрівер
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter licence category");
        String lc = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        int k = 0;
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null) {
                if (drivers[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        drivers[k].setAge(age);
        drivers[k].setName(n);
        drivers[k].setLicenceCategory(lc);
        return drivers[k];
    }

    public Driver[] delete(String id) throws IOException {
        int k = 0;
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null) {
                System.out.println(drivers[i].getId().equalsIgnoreCase(id));
                if (drivers[i].getId().equalsIgnoreCase(id)) {
                    k = i;
                }
            }
        }
        System.out.println("k = " + k);
        Driver[] a = Arrays.copyOfRange(drivers, 0, k);
        Driver[] b = Arrays.copyOfRange(drivers, k + 1, drivers.length);
        Driver[] drivers = Arrays.copyOf(a, a.length + b.length + 1);
        System.arraycopy(b, 0, drivers, a.length, b.length);
        return this.drivers = drivers;
    }
}