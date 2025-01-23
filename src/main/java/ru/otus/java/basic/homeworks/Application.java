package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Printing p = new Printing();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printCharacter('A', 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printCharacter('B',2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    p.printCharacter('C',0);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
