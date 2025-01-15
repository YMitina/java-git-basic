package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {

        System.out.println();
        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("Вес первой коробки яблок =" + appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println("Вес первой коробки апельсинов =" + orangeBox.getWeight());
        System.out.println("");
        System.out.println("Сравниваем первую коробку аппельсино и первую коробку яблок по весу");
        System.out.println( appleBox.compare(orangeBox)? "Вес коробок одинаковый!" :"Вес коробок разный");
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.add(new Orange());
        System.out.println("Вес второй коробки апельсинов =" + orangeBox2.getWeight());
        System.out.println("");
        System.out.println("Пересыпаем апельсины из первой коробки во вторую:");
        orangeBox.fill(orangeBox2);
        System.out.println("Вес первой коробки апельсинов =" + orangeBox.getWeight());
        System.out.println("Вес второй коробки апельсинов =" + orangeBox2.getWeight());
        System.out.println("");
        System.out.println("Пересыпаем в корзину с фруктами яблоки:");
        Box<Fruit> fruitBox = new Box<>();
        fruitBox.fill(appleBox);
        System.out.println("Вес первой коробки фруктов =" + fruitBox.getWeight());
        System.out.println("Вес первой коробки яблок =" + appleBox.getWeight());
        System.out.println("");
        System.out.println("Пересыпаем в корзину с фруктами первую коробку апельсинов:");
        fruitBox.fill(orangeBox);
        System.out.println("Вес первой коробки фруктов =" + fruitBox.getWeight());
        System.out.println("Вес первой коробки апельсинов =" + orangeBox.getWeight());



    }

}
