package org.module3;

import org.module3.controller.Controller;
import org.module3.factory.JdbcFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JdbcFactory.getInstance().initDB(Main.class);
        new Controller().start();
    }
}