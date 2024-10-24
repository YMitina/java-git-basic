package ru.otus.java.basic.homeworks.homeworks_2;

import java.util.Arrays;
import java.util.Scanner;

public class MainApplicationArray {
    public static void main(String[] args) {
        System.out.println("Задача №1");
        repeatPrint(3, "HELLO!");

        System.out.println("\nЗадача №2");
        int[] arrIn = {10, 15, 1, 25, 100, 40, 2, 4, 3, 3};
        printSumMoreFiveArray(arrIn);

        System.out.println("\nЗадача №3");
        int[] intArray = new int[5];
        fillOutArray(5, intArray);

        System.out.println("\nЗадача №4");
        int[] arr = {1,8,9};
        increaseArray(10, arr);

        System.out.println("\nЗадача №5");
        halfMoreArray(arrIn);

        System.out.println("\nЗадача №1*");
        int[] arrOne = {10, 15, 1};
        int[] arrTwo = {100, 40, 2, 4, 3, 3};
        int[] arrThree = {10, 15, 1, 25, 100, 40, 2, 4, 3, 3};
        sumArray(arrOne, arrTwo, arrThree);

        System.out.println("\nЗадача №3*");
        System.out.println("Введите 1 - если хотите произвести проверку элементов массива на убывание, 2 - на возрастание ");
        Scanner scanner = new Scanner(System.in);
        int typeCheck = scanner.nextInt();
        int[] checkArr = {10, 5, 1};
        checkValueArray(typeCheck, checkArr);

        System.out.println("\nЗадача №4*");
        int[] converselyArr = {11, 66, 77, 99};
        converselyArray(converselyArr);
    }

    //1
    public static void repeatPrint(int countRepit, String stringRepit) {
        for (int i = 0; i < countRepit; i++) {
            System.out.println(stringRepit);
        }

    }

    //2
    public static void printSumMoreFiveArray(int[] intArray) {
        int sum = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > 5) {
                sum += intArray[i];
            }
        }
        System.out.println("summa = " + sum);
    }

    //3
    public static void fillOutArray(int number, int... intArray) {
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = number;
            System.out.println("intArray[" + i + "]=" + intArray[i]);
        }
        System.out.println(Arrays.toString(intArray));
    }

    //4
    public static void increaseArray(int number, int[] Arr) {
        for (int i = 0; i < Arr.length; i++) {
            Arr[i] += number;
        }
        System.out.println(Arrays.toString(Arr));
    }

    //5
    public static void halfMoreArray(int[] intArray) {
        int sumLeft = 0, sumRight = 0;
        if (intArray.length % 2 != 0) {
            System.out.println("Не четное Количество элементов в массиве, невозможно сравнить суммы половин элементов массива!!!");
        } else {
            for (int i = 0; i < intArray.length; i++) {
                if (i < intArray.length / 2) {
                    sumLeft += intArray[i];
                } else {
                    sumRight += intArray[i];
                }
            }
            if (sumLeft > sumRight) {
                System.out.println("Сумма элементов левой половины больше правой!");
            } else if (sumLeft < sumRight) {
                System.out.println("Сумма элементов правой половины больше левой!");
            } else {
                System.out.println("Сумма элементов правой и левой половины равны!");
            }
        }

    }

    //1*
    //********************************************************************************
    public static void sumArray(int[] intArrayOne, int[] intArrayTwo, int[] intArrayThree) {

        int indexMax = Math.max(intArrayOne.length, intArrayTwo.length);
        indexMax = Math.max(intArrayThree.length, indexMax);
        int[] intArraySum = new int[indexMax];

        for (int i = 0; i < intArrayOne.length; i++) {
            intArraySum[i] += intArrayOne[i];
        }
        for (int i = 0; i < intArrayTwo.length; i++) {
            intArraySum[i] += intArrayTwo[i];
        }
        for (int i = 0; i < intArrayThree.length; i++) {
            intArraySum[i] += intArrayThree[i];
        }
        System.out.println("Входящие массивы: ");
        System.out.println(Arrays.toString(intArrayOne));
        System.out.println(Arrays.toString(intArrayTwo));
        System.out.println(Arrays.toString(intArrayThree));
        System.out.print("Массив суммы входящих массивов: ");
        System.out.println(Arrays.toString(intArraySum));
    }

    //3*
    //********************************************************************************
    public static void checkValueArray(int  typeCheck, int[] intArray) {
        boolean check = true;
        for (int i = 0; i < intArray.length - 1; i++) {
            if (typeCheck == 1) {
                if (intArray[i] > intArray[i + 1]) {
                    System.out.println("Порядок убывания элементов массива нарушен");
                    check = false;
                    break;
                }
            }
            if (typeCheck == 2) {
                if (intArray[i] < intArray[i + 1]) {
                    System.out.println("Порядок возрастания элементов массива нарушен");
                    check = false;
                    break;
                }
            }
        }
        if (check && typeCheck == 1) {
            System.out.println("Массив упорядочен по убыванию!");
        } else if (check && typeCheck == 2) {
            System.out.println("Массив упорядочен по взрастанию!");
        } else if (typeCheck != 1 && typeCheck != 2) {
            System.out.println("Некорректные входные данные, не удалось провести проверку!!!");
        }
    }

    //4*
    //********************************************************************************
//    public static void converselyArray(int[] inArray) {
//        int lengthInArr = inArray.length;
//        int[] outArray = new int[lengthInArr];
//        for (int i = 0, j = lengthInArr - 1; i < inArray.length; i++, j--) {
//            outArray[j] = inArray[i];
//        }
//        System.out.print("Исходный массив:");
//        System.out.println(Arrays.toString(inArray));
//        System.out.print("Перевернутый массив:");
//        System.out.println(Arrays.toString(outArray));
//    }
    public static void converselyArray(int[] inArray) {
        System.out.print("Исходный массив:");
        System.out.println(Arrays.toString(inArray));
        System.out.println("Перевернутый массив:");
        System.out.print("[");
        for (int i = inArray.length-1; i>= 0; i--) {
            System.out.print(inArray[i]);
            if (i != 0){
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
}
