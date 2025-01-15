package ru.otus.java.basic.homeworks;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;
    private int weight;

    public Box() {
        this.fruits = new ArrayList<T>();
    }

    public int getWeight() {
        return weight;
    }

    public void add(T fruit) {
        fruits.add(fruit);
        weight += fruit.getWeight();
    }

    public boolean compare(Box<? extends Fruit> box) {

        if (Math.abs(this.weight - box.weight) < 0.0000001) {
            return true;
        }
        return false;
    }

    public void fill(Box<? extends Fruit> box) {
        for (Fruit f : box.fruits) {
            this.add((T) f);
        }

        box.fruits.clear();
        box.weight = 0;
    }


}

