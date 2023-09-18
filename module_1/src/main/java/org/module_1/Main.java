package org.module_1;

import org.module_1.controller.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.start();
    }
}
