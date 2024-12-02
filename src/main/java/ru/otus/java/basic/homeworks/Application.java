package ru.otus.java.basic.homeworks;

import java.util.*;

import static ru.otus.java.basic.homeworks.PhoneBook.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("Исходная телефонная книга:");
        Comparator<RecordPhoneBook> phoneComparator = Comparator.comparing(RecordPhoneBook::getPhone);
        TreeSet<RecordPhoneBook> phoneBookTreeSet = new TreeSet<>(phoneComparator);
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 806 88 89", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 806 88 88", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 805 77 77", "ПЕТРОВ ПЕТР ПЕТРОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 508 054 44 84", "СИДОРОВ СИДОР СИДОВИЧ"));
        System.out.println("Телефонная книга после добавления нового контакта:");
        for (RecordPhoneBook phoneBook : phoneBookTreeSet) {
            System.out.println("номер телефона: " + phoneBook.getPhone() + " ФИО: " + phoneBook.getName());
        }

        /**
         * Добавление нового контакта
         */
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add(phoneBookTreeSet, "8 950 805 99 99", "КРЫЛОВ ИВАН ИВАНОВИЧ");
        System.out.println("Телефонная книга:");

        for (RecordPhoneBook recordPhoneBook : phoneBookTreeSet) {
            System.out.println("номер телефона: " + recordPhoneBook.getPhone() + " ФИО: " + recordPhoneBook.getName());
        }

        /**
         * Поиск контакта по имени
         */
        System.out.println();
        String findName = "ИВАНОВ ИВАН ИВАНОВИЧ";
        System.out.println("Поиск Контакта:");
        System.out.println(findName);
        System.out.println("Найденные номера: ");
        phoneBook.find(phoneBookTreeSet, findName);

        /**
         * Проверка наличия номера в телефоной книге
         */
        System.out.println();
        String findPhone = "8 950 805 77 77";
        System.out.println("Номер телефона " + findPhone + (containsPhoneNumber(phoneBookTreeSet, findPhone) ? " найден!" : " не найден!"));
    }


}

