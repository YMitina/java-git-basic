package ru.otus.java.basic.homeworks;

public class Cat {
    String name;
    int appetite;
    boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.fullness = false;
        this.appetite = appetite;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public void setFullness(boolean fullness) {
        this.fullness = fullness;
    }

    protected void info() {
        System.out.println("Кот " + name + ", состояние его сытости: " + fullness);
    }

}
