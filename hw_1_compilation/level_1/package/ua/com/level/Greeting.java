package ua.com.level;

public class Greeting {

    public static void main(String[] args) {
        Printer p = new Printer();
        byte b = Byte.MAX_VALUE;
        byte b1 = Byte.MIN_VALUE;
        short s = Short.MAX_VALUE;
        int a = Integer.MAX_VALUE;
        long l = Long.MAX_VALUE;
        p.print(String.valueOf(b));
        p.print(String.valueOf(b1));
        p.print(String.valueOf(s));
        p.print(String.valueOf(a));
        p.print(String.valueOf(l));
    }

}