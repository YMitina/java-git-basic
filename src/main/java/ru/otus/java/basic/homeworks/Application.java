package ru.otus.java.basic.homeworks;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        double arr[] = new double[100_000_000];
        Array array = new Array();

        Thread t1 = new Thread(() -> {
            array.filling(arr, 0, 25_000_000, 1);
        });

        Thread t2 = new Thread(() -> {
            array.filling(arr, 25_000_000, 50_000_000, 2);
        });

        Thread t3 = new Thread(() -> {
            array.filling(arr, 50_000_000, 75_000_000, 3);
        });

        Thread t4 = new Thread(() -> {
            array.filling(arr, 75_000_000, 100_000_000, 4);
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}


