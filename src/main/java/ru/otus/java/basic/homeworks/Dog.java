package ru.otus.java.basic.homeworks;

public class Dog extends Animals{
    public Dog(String name,
               int speedRun,
               int speedSwim,
               int power) {
        this.name = name;
        this.speedRun = speedRun;
        this.speedSwim = speedSwim;
        this.power = power;
    }
    @Override
    public int swim(int distance){
        int time;
        if (power < distance) {
            System.out.println( name + " испытывает состояние усталости, ему не проплыть " + distance + "м. Он готов проплыть не более "+ power/2+"м.");
            return -1;
        } else {
            power -= distance*2;
            time = (int) distance / speedSwim;
            System.out.println( name + " проплыл расстояние " + distance + "м.");
            return time;
        }
    }
}
