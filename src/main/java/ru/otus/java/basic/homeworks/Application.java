package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        double arr[] = new double[100_000_000];
        Array array = new Array();
        long time = System.currentTimeMillis();
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

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Время выполнения в 4-х потоках: " + (System.currentTimeMillis() - time));

    }

}


