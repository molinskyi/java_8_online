package org.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type line");
        String s = reader.readLine();
        Methods.addNumbers(s);
        System.out.println();
        Methods.countLetters(s);
        System.out.println("Type number of lesson from 1 to 10");
        String lesson = reader.readLine();
        Methods.lessonTime(Integer.valueOf(lesson));
    }
}