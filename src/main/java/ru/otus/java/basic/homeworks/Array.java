package ru.otus.java.basic.homeworks;

import java.time.LocalTime;

public class Array {
    public void filling(double arr[], int startIndex, int endIndex, int numberThread) {
        if (numberThread != 0) {
            System.out.println("Время начала выполнения " + numberThread + " потока:" + LocalTime.now());
        } else {
            System.out.println("Время начала выполнения в одном потоке:" + LocalTime.now());
        }

        for (int i = startIndex; i < endIndex; i++) {
            arr[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }

        if (numberThread != 0) {
            System.out.println("Время окончания выполнения " + numberThread + " потока:" + LocalTime.now());
        } else {
            System.out.println("Время окончания выполнения в одном потоке:" + LocalTime.now());
        }
    }

    public static void main(String[] args) {
        Array array = new Array();
        double arr[] = new double[100_000_000];
        array.filling(arr, 0, 100_000_000, 0);
    }
}
