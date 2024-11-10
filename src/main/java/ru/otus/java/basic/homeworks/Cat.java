package ru.otus.java.basic.homeworks;

public class Cat extends Animals {
    public Cat(String name,
             int speedRun,
             int power) {
        this.name = name;
        this.speedRun = speedRun;
        this.power = power;
    }
    @Override
    public int swim(int distance)
    {
        System.out.println("Коты не умеют плавать");
        return -1;
    }
}
