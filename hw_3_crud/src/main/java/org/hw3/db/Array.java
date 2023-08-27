package org.hw3.db;

public class Array {

    private int dynamicArray[];
    private int x;
    Array(int arrayLength) {
        dynamicArray = new int[arrayLength]; }
    public void displayArray(){
        for (int i = 0; i < x; i++) {
            System.out.print(dynamicArray[i] + " ");
        }}
    public void add(int item){
        if (dynamicArray.length == x) {
            int newArr[] = new int[2 * x];
            for (int i = 0; i < x; i++) {
                newArr[i] = dynamicArray[i];
            }
            dynamicArray = newArr;}
        dynamicArray[x++] = item;
    }
}
