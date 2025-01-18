package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruitsList;
    private int weight;

    public Box() {
        this.fruitsList = new ArrayList<T>();
    }

    public int getWeight() {
        return weight;
    }

    public void add(T fruit) {
        fruitsList.add(fruit);
        weight += fruit.weight;

    }

    public int compare(Box<?> box) {
        return Math.abs(this.weight - box.weight);

    }

    public void fill(Box<? extends T> box) {
        for (Fruit f : box.fruitsList) {
            this.add((T) f);
        }
       box.fruitsList.clear();
       box.weight = 0;
    }
    public void fill2(Box<? super T> box) {
        for (Fruit f : this.fruitsList) {
            box.add((T)f);
        }
        this.fruitsList.clear();
        this.weight = 0;
    }


}

