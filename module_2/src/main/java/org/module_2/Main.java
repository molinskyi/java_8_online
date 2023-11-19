package org.module_2;

import org.module_2.service.Service;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Service service = new Service();
        service.findMinPrice();
    }
}
