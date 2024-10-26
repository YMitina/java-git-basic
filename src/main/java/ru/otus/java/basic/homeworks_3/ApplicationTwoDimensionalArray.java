package ru.otus.java.basic.homeworks_3;

public class ApplicationTwoDimensionalArray {
    public static void main(String[] args) {
        System.out.println("Задача №1");
        int[][] inArray = {{1,-8,5},{0,5,-9},{-9,-8}};
        int sumOfPositive = sumOfPositiveElements(inArray);
        System.out.println("Сумма положительных элементов массива равна: " + sumOfPositive);

        System.out.println("Задача №2");
        printSquare(5);

        System.out.println("Задача №3");
        int [][] inSquareArray = {{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        printDiagonalInSquare(inSquareArray);

        System.out.println("Задача №4");
        int[][] inArr = {{111,-8,51},{10,5,-9},{-91,-8}};
        int maxElements = searchMaxElements(inArr);
        System.out.println("Максимальный элемент в массиве: " + maxElements);

        System.out.println("Задача №5");
        int sumElementsTwoStr = sumElementsTwoString(inArr);
        System.out.println("Сумма элемент в массиве во второй строке равна: " + sumElementsTwoStr);

    }
    //1
    public static int sumOfPositiveElements(int[][] inArray) {
        int sum = 0;
        for (int i = 0; i < inArray.length; i++) {
            for (int j = 0; j < inArray[i].length; j++) {
                if (inArray[i][j] > 0 ) {
                    sum += inArray[i][j];
                }
            }
        }
        return sum;
    }
    //2
    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }
    //3
    public static void printDiagonalInSquare(int[][] inSquareArray) {
        for (int i = 0; i < inSquareArray.length; i++) {
            for (int j = 0; j < inSquareArray[i].length; j++) {
                if (i == j || j == inSquareArray[i].length - 1 - i) {
                    inSquareArray[i][j] = 0;
                }
                System.out.print(inSquareArray[i][j]);
            }
            System.out.println();
        }
    }
    //4
    public static int searchMaxElements(int[][] inArr) {
        int max = 0;
        for (int i = 0; i < inArr.length; i++) {
            for (int j = 0; j < inArr[i].length; j++) {
                if (inArr[i][j] > max ) {
                    max = inArr[i][j];
                }
            }
        }
        return max;
    }
    //5
    public static int sumElementsTwoString(int[][] inArr) {
        int sum = 0;
        if (inArr.length < 2) {
            return -1;
        }
        for (int j = 0; j < inArr[1].length; j++) {
            sum += inArr[1][j];
        }
        return sum ;
    }
}

