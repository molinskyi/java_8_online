package org.hw6;

public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary<>(10);
        Dictionary dictionary1 = new Dictionary<>(10);
        dictionary1.put(0,15);
        dictionary1.put(1,76);
        dictionary.put(2,45);
        dictionary.put(3,5);
        dictionary.put(4,12);
        dictionary.put(2,45);
        dictionary.put(3,5);
        dictionary.put(4,12);
        dictionary.put(2,45);
        dictionary.put(3,5);
        dictionary.put(4,12);
        //dictionary.remove(4);
        dictionary.putAll(dictionary1);
        System.out.println(dictionary.values());
    }
}
