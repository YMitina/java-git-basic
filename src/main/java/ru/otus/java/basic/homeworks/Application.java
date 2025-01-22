package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Printing p = new Printing();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printA();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printB();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printC();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t3.start();
    }
}
