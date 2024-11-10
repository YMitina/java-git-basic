package ru.otus.java.basic.homeworks;

public abstract class Animals {
    String name;
    int speedRun;
    int speedSwim;
    int power;

    public void info() {
        System.out.println("Состояние выносливость животного по имени " + name + " состовляет " + power + " единиц");
        if (power == 0) {
            System.out.println("Состояние выносливость животного по имени " + name +" на нуле, надо отдохнуть!");
        }
    }

    public int run(int distance) {
        int time;
        if (power < distance) {
                  System.out.println( name + " испытывает состояние усталости, ему не побежать " + distance + "м. Он готов побежать не более "+ power+"м.");
                  return -1;
        } else {
            power -= distance;
            time = (int) distance / speedRun;
            System.out.println( name + " совершил пробежку на расстояние " + distance + "м.");
            return time;
        }
    }

    public abstract int swim(int distance);

}

