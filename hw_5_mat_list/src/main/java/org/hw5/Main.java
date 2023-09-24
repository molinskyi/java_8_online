package org.hw5;

public class Main {
    public static void main(String[] args) {
        Object[] arr = new Object[4];
        Number[] number = new Number[]{1, 6};
        MatList matList = new MatList<>(new Integer[]{1, 12535, 235, 23, 5623, 623, 6, 24, 62, 34});
        MatList matList1 = new MatList<>();
        matList.add(-660);
        matList1.add(-10, 1, 2, -6);
        MatList matList2 = new MatList<>(matList1, matList);
        matList.clear(number);
        System.out.println(matList.getNumbers());
    }
}
