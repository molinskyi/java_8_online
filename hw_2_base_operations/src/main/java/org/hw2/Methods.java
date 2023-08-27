package org.hw2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Methods {
    public static void addNumbers(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                res += Character.getNumericValue(s.charAt(i));
            }
        }
        System.out.println("res = " + res);
    }

    public static void countLetters(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                counter++;
            }
        }

        switch (counter) {
            case 0:
                System.out.println("No letters");
                break;
        }

        char[] arr = new char[counter];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                arr[counter - 1] = s.charAt(i);
                counter--;
            }
        }

        Map<Object, Integer> count = new HashMap<>();
        for (char x : arr) {
            Integer newValue = count.getOrDefault(x, 0) + 1;
            count.put(x, newValue);
        }
        System.out.println(count);
    }

    public static void lessonTime(int lesson) {
        int a = lesson * 45 + (lesson / 2) * 5 + ((lesson + 1) / 2 - 1) * 15 + 540;
        System.out.println(a / 60 + "," + a % 60);
    }
}
