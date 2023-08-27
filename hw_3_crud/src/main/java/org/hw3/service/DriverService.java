package org.hw3.service;

import org.hw3.db.DriverDb;
import org.hw3.entity.Driver;

public class DriverService {

    private DriverDb driverDb = new DriverDb();

    public void create(String name, String licenceCategory, int age) {
        Driver driver = new Driver();
        driver.setAge(age);
        driver.setName(name);
        driver.setLicenceCategory(licenceCategory);
        driverDb.create(driver);
    }

    public Driver[] findAll() {
        return driverDb.findAll();
    }

}
