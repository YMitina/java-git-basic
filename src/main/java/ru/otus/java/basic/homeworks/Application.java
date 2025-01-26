package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("Начинает работу метод main");
        Array a = new Array();
        int[] arr = {2, 2, 1, 2};
        try {
            int[] newArr = a.getArrayAfterTheLastUnit(arr);
            System.out.println(Arrays.toString(newArr));
            LOGGER.info("Метод getArrayAfterTheLastUnit отработал. Новый массив = {}", newArr);
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка метода getArrayAfterTheLastUnit! {}", e.getMessage());
        }

        boolean resultCheck = a.checkArrayOnlyOneAndTwo(arr);
        System.out.println("Массив состоит только из 1 и 2  - это " + (resultCheck ? "ИСТИНА" : "ЛОЖЬ"));
        LOGGER.info("Закончил работу метод main");
    }


}
