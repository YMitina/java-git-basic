package ru.otus.java.basic.homeworks;

public class Printing {
    private int flag = 0;

    public synchronized void printCharacter(char character, int nextState) throws InterruptedException {
        int currentState = 0;
        if (nextState == 0) {
            currentState = 2;
        } else {
            currentState = nextState - 1;
        }
        while (flag != currentState) {
            wait();
        }
        System.out.print(character);
        flag = nextState;
        notifyAll();
    }

}
