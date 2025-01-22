package ru.otus.java.basic.homeworks;

public class Printing {
    private int flag = 0;
    public synchronized void printA() throws InterruptedException {
         while (flag !=0){
             wait();
         }
         System.out.print('A');
         flag=1;
         notifyAll();
     }
    public synchronized void printB() throws InterruptedException {
        while (flag !=1){
            wait();
        }
        System.out.print('B');
        flag=2;
        notifyAll();
    }
    public synchronized void printC() throws InterruptedException {
        while (flag !=2){
            wait();
        }
        System.out.print('C');
        flag=0;
        notifyAll();
    }
}
