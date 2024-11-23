package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {
        String[][] arrString = new String[][]{{"1","1", "1", "1"}, {"5", "5", "5", "5"}, {"10", "10", "10", "10"}, {"20", "20", "20", "20"}};
        try {
            int sumArr = sumArr(arrString);
            System.out.println("Сумма элементов массива = " + sumArr);
        } catch (AppArrayDataException | AppArraySizeException e) {
            System.out.println("Не удалось посчитать сумму элементов массива!");
        }
    }

    public static int sumArr(String[][] arr) throws AppArraySizeException, AppArrayDataException {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr.length != 4 || arr[i].length != 4) {
                    throw new AppArraySizeException("Некооректный размер входящего массива! Необходимо на вход подать массив размером 4*4!");
                }
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new AppArrayDataException("Не возможно привести значение \"" + arr[i][j] + "\" в ячейки массива " + "[" + (++i) + "][" + (++j) + "] к числовумо типу!");
                }
            }
        }
        return sum;
    }
}


