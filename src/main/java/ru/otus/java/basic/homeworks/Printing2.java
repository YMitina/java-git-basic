package ru.otus.java.basic.homeworks;

public class Printing2 {
    public synchronized void printA() {
        System.out.print('A');
    }
    public synchronized void printB()  {
        System.out.print('B');
    }
    public synchronized void printC() {
        System.out.print('C');
    }
}
