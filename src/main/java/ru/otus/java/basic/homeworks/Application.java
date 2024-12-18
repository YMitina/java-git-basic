package ru.otus.java.basic.homeworks;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Привет мир!!!");
        PersonDataBase personDataBase = new PersonDataBase();
        personDataBase.add(new Person("Иванов Иван Иванович", 11L));
        personDataBase.add(new Person("Петров Петр Петрович", 12L));
        personDataBase.add(new Person("Митин Иван Иванович", 13L));
        personDataBase.add(new Person("Козлов Петр Петрович", 14L));
        System.out.println("Сотрудники:");
        for (Person persons : personDataBase.mapPerson.values()) {
            System.out.println(persons.toString());
        }
        System.out.println("");
        System.out.println("Сотрудник с табельным номером 11:");
        personDataBase.findById(11L).toString();
        System.out.println("");
        System.out.println("Менеджеры:");
        for (Person persons : personDataBase.mapPerson.values()) {
            if (personDataBase.isManager(persons)) {
                System.out.println(persons.toString());
            }
        }
        System.out.println("");
        System.out.println("Оставшиеся сотрудники(не менеджеры):");
        for (Person persons : personDataBase.mapPerson.values()) {
            if (personDataBase.isEmployee(persons)) {
                System.out.println(persons.toString());
            }
        }

        System.out.println("");
        int [] arr = new int[] {5,6,7,1,9};
        System.out.println("Массив до сортировки: " + Arrays.toString(arr));
        SortArray.sort(arr);
        System.out.println("Массив после сортировки: " +Arrays.toString(arr));

    }
}

