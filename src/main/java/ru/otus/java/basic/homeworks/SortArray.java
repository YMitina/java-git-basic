package ru.otus.java.basic.homeworks;

public class SortArray {
    public static void sort(int[] arr) {
        for (int j = 1; j < arr.length; j++) {
            boolean isSorted = true;
            for (int i = 0; i < arr.length - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int  temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


}
