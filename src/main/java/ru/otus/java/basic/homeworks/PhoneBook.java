package ru.otus.java.basic.homeworks;

import java.util.Objects;
import java.util.TreeSet;

public class PhoneBook {

    static void add(TreeSet<RecordPhoneBook> phoneBookTreeSet, String phone, String name) {
        phoneBookTreeSet.add(new RecordPhoneBook(phone, name));
    }

    static void find(TreeSet<RecordPhoneBook> phoneBookTreeSet, String name) {
        boolean flagIsFind = false;
        for (RecordPhoneBook phoneBook : phoneBookTreeSet) {
            if (phoneBook.getName().equals(name)) {
                System.out.println(phoneBook.getPhone());
                flagIsFind = true;
            }
        }
        if (!flagIsFind) {
            System.out.println("ОТСУТСТВУЮТ");
        }
    }

    static boolean containsPhoneNumber(TreeSet<RecordPhoneBook> phoneBookTreeSet, String phone) {
        for (RecordPhoneBook phoneBook : phoneBookTreeSet) {
            if (phoneBook.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

}
