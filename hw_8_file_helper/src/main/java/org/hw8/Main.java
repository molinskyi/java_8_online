package org.hw8;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(FileHelper.startLink);
        FileHelper fileHelper = new FileHelper();
        fileHelper.start();
    }
}