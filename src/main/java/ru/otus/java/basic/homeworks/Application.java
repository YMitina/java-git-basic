package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {
        //  System.out.println("Hello world!");
        //  System.out.println("Привет мир!!!");

        Cat cat = new Cat("Kompot", 10, 50);
        System.out.println("Рассказ про котика по имени " + cat.name + "!");
        cat.info();
        int timeRunCat = cat.run(25);
        if (timeRunCat != -1) {
            System.out.println(cat.name + " бегал " + timeRunCat + "с.");
        }
        cat.info();
        cat.swim(8);
        System.out.println();

        Dog dog = new Dog("Reks", 10, 5, 100);
        System.out.println("Рассказ про собачку по имени " + dog.name + "!");
        dog.info();
        int timeSwimDog = dog.swim(10);
        if (timeSwimDog != -1) {
            System.out.println(dog.name + " плавал " + timeSwimDog + "с.");
        }
        dog.info();
        System.out.println();

        Horse horse = new Horse("Almaz", 50, 10, 1000);
        System.out.println("Рассказ про лошадку по имени " + horse.name + "!");
        horse.info();
        int timeSwimHorse = horse.swim(100);
        if (timeSwimHorse != -1) {
            System.out.println(horse.name + " плавал " + timeSwimHorse + "с.");
        }
        horse.info();
        int timeSwimRun = horse.run(1000);
        if (timeSwimRun != -1) {
            System.out.println(horse.name + " плавал " + timeSwimRun + "с.");
        }
        horse.info();



    }

}
