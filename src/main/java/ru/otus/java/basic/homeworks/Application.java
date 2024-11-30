package ru.otus.java.basic.homeworks;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("Исходная телефонная книга:");
        Comparator<PhoneBook> phoneComparator = Comparator.comparing(PhoneBook::getPhone);
        TreeSet<PhoneBook> phoneBookTreeSet = new TreeSet<>(phoneComparator);
        phoneBookTreeSet.add(new PhoneBook("8 950 806 88 89", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new PhoneBook("8 950 806 88 88", "ИВАНОВ ИВАН ИВАНОВИЧ"));
        phoneBookTreeSet.add(new PhoneBook("8 950 805 77 77", "ПЕТРОВ ПЕТР ПЕТРОВИЧ"));
        phoneBookTreeSet.add(new PhoneBook("8 508 054 44 84", "СИДОРОВ СИДОР СИДОВИЧ"));
        System.out.println("Телефонная книга после добавления нового контакта:");
        for (PhoneBook phoneBook : phoneBookTreeSet) {
            System.out.println("номер телефона: " + phoneBook.getPhone() + " ФИО: " + phoneBook.getName());
        }

        /**
         * Добавление нового контакта
         */
        add(phoneBookTreeSet, "8 950 805 99 99", "КРЫЛОВ ИВАН ИВАНОВИЧ");
        System.out.println("Телефонная книга:");

        for (PhoneBook phoneBook : phoneBookTreeSet) {
            System.out.println("номер телефона: " + phoneBook.getPhone() + " ФИО: " + phoneBook.getName());
        }

        /**
         * Поиск контакта по имени
         */
        System.out.println();
        String findName = "ИВАНОВ ИВАН ИВАНОВИЧ";
        System.out.println("Поиск Контакта:");
        System.out.println(findName);
        System.out.println("Найденные номера: ");
        find(phoneBookTreeSet, findName);

        /**
         * Проверка наличия номера в телефоной книге
         */
        System.out.println();
        String findPhone = "89508057777";
        System.out.println("Номер телефона " + findPhone + (containsPhoneNumber(phoneBookTreeSet, findPhone) ? " найден!" : " не найден!"));
    }

    static void add(TreeSet<PhoneBook> phoneBookTreeSet, String phone, String name) {
        phoneBookTreeSet.add(new PhoneBook(phone, name));
    }

    static void find(TreeSet<PhoneBook> phoneBookTreeSet, String name) {
        boolean flagIsFind = false;
        for (PhoneBook phoneBook : phoneBookTreeSet) {
            if (phoneBook.getName().equals(name)) {
                System.out.println(phoneBook.getPhone());
                flagIsFind = true;
            }
        }
        if (!flagIsFind) {
            System.out.println("ОТСУТСТВУЮТ");
        }
    }

    static boolean containsPhoneNumber(TreeSet<PhoneBook> phoneBookTreeSet, String phone) {
        for (PhoneBook phoneBook : phoneBookTreeSet) {
            if (phoneBook.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }
}

