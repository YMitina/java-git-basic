package ru.otus.java.basic.homeworks;

import java.util.*;

import static ru.otus.java.basic.homeworks.PhoneBook.*;

public class Application {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("ИВАНОВ ИВАН ИВАНОВИЧ", "8 950 806 88 89");
        phoneBook.add("ИВАНОВ ИВАН ИВАНОВИЧ", "8 950 806 88 88");
        phoneBook.add("ПЕТРОВ ПЕТР ПЕТРОВИЧ", "8 950 805 77 77");
        phoneBook.add("СИДОРОВ СИДОР СИДОВИЧ", "8 508 054 44 84");

        System.out.println("Исходная телефонная книга:");

        phoneBook.print();

        /**
         * Добавление нового контакта
         */
        phoneBook.add("КРЫЛОВ ИВАН ИВАНОВИЧ", "8 950 805 99 99");
        phoneBook.add("ИВАНОВ ИВАН ИВАНОВИЧ", "8 950 805 55 55");
        System.out.println("Телефонная книга после добавления новых контактов:");
        phoneBook.print();

        /**
         * Поиск контакта по имени
         */
        System.out.println();
        String findName = "ИВАНОВ ИВАН ИВАНОВИЧ";
        System.out.println("Поиск контакта:");
        System.out.println(findName);
        System.out.println("Найденные номера: " + phoneBook.find(findName));


        /**
         * Проверка наличия номера в телефоной книге
         */
        System.out.println();
        String findPhone = "8 950 805 99 99";
        System.out.println("Номер телефона " + findPhone + (phoneBook.containsPhoneNumber(findPhone) ? " найден!" : " не найден!"));
    }


}

