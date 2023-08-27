package org.hw3;

import org.hw3.controller.DriverController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DriverController driverController = new DriverController();
        driverController.start();
    }
}
