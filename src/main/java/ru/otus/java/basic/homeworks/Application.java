package ru.otus.java.basic.homeworks;

import java.util.*;

import static ru.otus.java.basic.homeworks.PhoneBook.*;

public class Application {
    public static void main(String[] args) {

        Comparator<RecordPhoneBook> phoneComparator = Comparator.comparing(RecordPhoneBook::getPhone);
        TreeSet<RecordPhoneBook> phoneBookTreeSet = new TreeSet<>(phoneComparator);
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 806 88 89", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 806 88 88", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 950 805 77 77", "ПЕТРОВ ПЕТР ПЕТРОВИЧ"));
        phoneBookTreeSet.add(new RecordPhoneBook("8 508 054 44 84", "СИДОРОВ СИДОР СИДОВИЧ"));


        PhoneBook phoneBook = new PhoneBook(phoneBookTreeSet);
        System.out.println("Исходная телефонная книга:");
        phoneBook.print();
        System.out.println();

        /**
         * Добавление нового контакта
         */
        phoneBook.add("8 950 805 99 99", "КРЫЛОВ ИВАН ИВАНОВИЧ");
        System.out.println("Телефонная книга после добавления нового контакта:");
        phoneBook.print();

        /**
         * Поиск контакта по имени
         */
        System.out.println();
        String findName = "ИВАНОВ ИВАН ИВАНОВИЧ";
        System.out.println("Поиск контакта:");
        System.out.println(findName);
        System.out.println("Найденные номера: ");
        ArrayList<String> listPhone = phoneBook.find(findName);
        System.out.println(listPhone);

        /**
         * Проверка наличия номера в телефоной книге
         */
        System.out.println();
        String findPhone = "8 950 805 77 77";
        System.out.println("Номер телефона " + findPhone + (phoneBook.containsPhoneNumber(findPhone) ? " найден!" : " не найден!"));
    }


}

