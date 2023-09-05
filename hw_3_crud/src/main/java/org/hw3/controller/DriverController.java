package org.hw3.controller;

import org.hw3.entity.Driver;
import org.hw3.service.DriverCrudService;
import org.hw3.service.impl.DriverCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DriverController {

    private DriverCrudService driverCrudService = new DriverCrudServiceImpl();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String position = "";
        while ((position) != null) {
            menu();
            position = bufferedReader.readLine();
            crud(position, bufferedReader);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("Create 1");
        System.out.println("Find 2");
        System.out.println("Close 3");
        System.out.println("Update 4");
        System.out.println("Delete 5");
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        String id = "";
        switch (position) {
            case ("1"):
                create(bufferedReader);
                break;
            case ("2"):
                findAll();
                break;
            case ("3"):
                System.exit(0);
                break;
            case ("4"):
                System.out.println("Type id if you are going to update");
                id = String.valueOf(bufferedReader.readLine());
                driverCrudService.findOne(id);
                driverCrudService.update(id);
                break;
            case ("5"):
                System.out.println("Type id if you are going to delete");
                id = String.valueOf(bufferedReader.readLine());
                driverCrudService.delete(id);
                break;
        }
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String n = reader.readLine();
        System.out.println("Please enter licence category");
        String lc = reader.readLine();
        System.out.println("Please enter age");
        int age = Integer.parseInt(reader.readLine());
        Driver driver = new Driver();
        if (0 <= age && age <= 150) {
            driver.setAge(age);
        } else {
            System.out.println("Illegal age input");
            driver.setAge(0);
        }
        if (!(n.equalsIgnoreCase("Idiot"))) {
            driver.setName(n);
        } else {
            driver.setName("");
            System.out.println("Illegal name input");
        }
        driver.setLicenceCategory(lc);
        driverCrudService.create(driver);
    }

    private void findAll() {
        Driver[] drivers = driverCrudService.findAll();
        for (int i = 0; i < drivers.length; i++) {
            Driver driver = drivers[i];
            if (driver != null) {
                System.out.println("Name = " + driver.getName());
                System.out.println("Licence category = " + driver.getLicenceCategory());
                System.out.println("Age = " + driver.getAge());
                System.out.println("student id= " + driver.getId());
                System.out.println();
            }
        }
    }
}
