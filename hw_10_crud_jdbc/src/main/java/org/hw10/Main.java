package org.hw10;

import org.hw10.controller.Controller;
import org.hw10.factory.JdbcFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JdbcFactory.getInstance().initDB(Main.class);
        Controller controller = new Controller();
        controller.start();
    }
}
