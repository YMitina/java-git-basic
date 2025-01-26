package ru.otus.java.basic.homeworks;

public class Array {
    public static int[] getArrayAfterTheLastUnit(int[] arr) {
        int indexLastUnit = -1;
        int i = 0;
        int j = 0;
        for (i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                indexLastUnit = i;
                break;
            }
        }
        if (indexLastUnit != -1) {
            int sizeNewArr = arr.length - indexLastUnit - 1;
            int[] newArr = new int[sizeNewArr];
            for (i = arr.length - 1, j = 0; j < sizeNewArr; i--, j++) {
                newArr[j] = arr[i];
            }
            return newArr;
        } else {
            throw new RuntimeException("Нет ни одной единицы!");

        }
    }

    public static boolean checkArrayOnlyOneAndTwo(int[] arr) {
        int countOne = 0, countTwo = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 1 && arr[i] != 2) {
                return false;
            } else if (arr[i] == 1) {
                countOne++;
            } else if (arr[i] == 2) {
                countTwo++;
            }
        }
        if (countOne == 0 || countTwo == 0) {
            return false;
        }
        return true;
    }
}
