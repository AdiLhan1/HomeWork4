package com.company;

import java.util.Random;

public class Main {

    public static int[] health = {1700, 200, 200, 220, 180, 650, 220, 220, 250};//0й элемент это босс
    public static int[] hits = {50, 20, 20, 20, 15, 10, 20, 20, 25};
    public static String[] hitTypes = {"Physical ", "Physical", "Magic ", "Mental", "Medic",
            "Tank", "Lovkach", "Berserk", "Tor"};

    public static void main(String[] args) {
        int i = 1;
        while (!isFinished()) {

            System.out.println("Round:" + i);
            changeBossDefence();
            round();
            printStatistics();
            i++;

        }
    }

    public static void round() {
        for (int i = 1; i <= 8; i++) {
            if (health[0] > 0) {
                if (health[i] > 0) {
                    int damagedHealthOfBoss = playerHit(i);
                    if (damagedHealthOfBoss < 0) {
                        health[0] = 0;
                    } else {
                        health[0] = damagedHealthOfBoss;
                    }
                } else {
                    health[0] = health[0];
                }
            }
        }
        if (health[0] > 0) {
            for (int i = 1; i <= 8; i++) {
                if (hitTypes[0].equals(hitTypes[5])) {
                    int tanksDefence = 25;
                    if (health[i] == 5) {
                        health[5] = bossHit(5) - tanksDefence * 7;
                    } else {
                        health[i] = bossHit(i) + tanksDefence;
                    }
                } else if (hitTypes[0].equals(hitTypes[6])) {
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }
                    if (health[i] == health[6]) {
                        health[i] = health[i];
                    } else {
                        health[i] = bossHit(i);
                    }
                } else if (hitTypes[0].equals(hitTypes[7])) {
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }
                    if (health[i] == health[7]) {
                        health[i] = bossHit(i) + 15;
                    } else {
                        health[i] = bossHit(i);
                    }
                } else if (hitTypes[0].equals(hitTypes[8])) {
                    if (health[i] <= 0) {
                        health[i] = 0;
                    }
                    health[i] = bossHit(i);

                } else {
                    if (health[i] <= 0) {
                        health[i] = 0;
                    } else {
                        health[i] = bossHit(i);
                    }
                }
                if (health[i] > 0) {
                    if (health[i] == health[4]) {
                        health[i] = health[i];
                    } else if (health[4] <= 0) {
                        health[i] = health[i];
                    } else {
                        health[i] = health[i] + 30;
                    }
                }
            }
        }

    }

    public static void printStatistics() {
        System.out.println("__________________________________");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Kinetic health: " + health[3]);
        System.out.println("Doctor health: " + health[4]);
        System.out.println("Tank health: " + health[5]);
        System.out.println("Lovkach health: " + health[6]);
        System.out.println("Berserk health: " + health[7]);
        System.out.println("Tor health: " + health[8]);
        System.out.println("__________________________________");
    }

    public static boolean isFinished() {

        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0 && health[5] <= 0 && health[6] <= 0 && health[7] <= 0 && health[8] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static int playerHit(int playerIndex) {
        Random r = new Random();
        int randomNumber = r.nextInt(7) + 2;
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            System.out.println(hitTypes[playerIndex] + "hits:" + randomNumber + hits[playerIndex] * randomNumber);
            if (hitTypes[playerIndex].equals(hitTypes[7])) {
                health[0] = health[0] - 15;
            }
            return health[0] - hits[playerIndex] * randomNumber;
        } else {
            return health[0] - hits[playerIndex];
        }
    }

    public static int bossHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[8])) {
            return health[playerIndex];
        } else
            return health[playerIndex] - hits[0];
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNum = r.nextInt(7) + 1;
        hitTypes[0] = hitTypes[randomNum];
    }

}