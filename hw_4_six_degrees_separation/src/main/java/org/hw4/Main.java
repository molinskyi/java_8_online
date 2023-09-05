package org.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int[][] relates = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}, //id
            {2, 4}, //1
            {1, 10, 16},//2
            {10, 5, 8},//3
            {22, 23},//4
            {3},//5
            {15, 16, 7},//6
            {6, 8, 18},//7
            {3, 7},//8
            {10, 12, 14, 27},//9
            {2, 3, 9, 14},//10
            {12, 13, 19},//11
            {9, 11, 25},//12
            {11, 17},//13
            {9, 10},//14
            {6, 20, 21, 26},//15
            {2, 6, 17},//16
            {16, 13, 22},//17
            {7, 17},//18
            {11},//19
            {15},//20
            {15},//21
            {4, 17},//22
            {4},//23
            {},//24
            {12},//25
            {15},//26
            {9}//27
    };

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= relates[0].length; i++) {
            for (int j = 0; j < relates[i].length; j++) {
                System.out.print(relates[i][j] + ", ");
            }
            System.out.println("id = " + i);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type start point");
        int id1 = Integer.valueOf(bufferedReader.readLine());
        System.out.println("Type end point");
        int id2 = Integer.valueOf(bufferedReader.readLine());
        send(relates, id1, id2);
    }

    static int counter = 0;

    private static void send(int relates[][], int idStart, int idEnd) {
        int idRun = idStart;
        int idPrev = 0;
        boolean track = false;
        for (int i = 0; i < 6; i++) {
            if (relates[idRun].length != 0) {
                if (idRun == idEnd) {
                    System.out.println("Got it");
                    track = true;
                    break;
                }

                idPrev = idRun;
                idRun = relates[idRun][0];
                if(relates[idRun].length == 0){
                    idRun = idPrev;
                }
                if(i == 5){
                    relates[idPrev] = Arrays.copyOfRange(relates[idPrev], 1, relates[idPrev].length);

                }

                if (relates[idRun].length != 0 && idPrev == relates[idRun][0]) {
                    relates[idRun] = Arrays.copyOfRange(relates[idRun], 1, relates[idRun].length);
                }


                System.out.println("Sending to " + idRun);
            }
        }
        if (track == false) {
            System.out.println("Stupid, try again");
            counter = counter + 1;
            if (counter < 60) {
                send(relates, idStart, idEnd);
            }
            if (counter >= 60) {
                System.out.println("Can't do that");
            }

        }
    }
}