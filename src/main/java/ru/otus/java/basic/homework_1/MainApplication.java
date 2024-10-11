package ru.otus.java.basic.homework_1;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {

        System.out.println("Введите число от 1 до 5!");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        switch (n) {
            case 1:
                greetings();
                break;
            case 2:
                checkSign((int) (Math.random() - 5), (int) (Math.random() * 15), (int) (Math.random() * 2));
                break;
            case 3:
                selectColor();
                break;
            case 4:
                compareNumbers();
                break;
            case 5:
                addOrSubtractAndPrint((int) (Math.random() * 5), (int) (Math.random() * 15), false);
                break;
            default:
                System.out.println("Ошибка ввода! Необходимо указать число в диапозоне от 1 до 5 включительно!");
                break;
        }
    }

    //1
    public static void greetings() {
        System.out.println("Hello");
        System.out.println("World");
        System.out.println("from");
        System.out.println("Java");
    }

    //2
    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    //3
    public static void selectColor() {
        int data = 8;
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data > 10 && data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    //4
    public static void compareNumbers() {
        int a = 8;
        int b = 10;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    //5
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        if (increment) {
            System.out.println(initValue + " + " + delta + " = " + (initValue + delta));
        } else {
            System.out.println(initValue + " - " + delta + " = " + (initValue - delta));
        }
    }
}
