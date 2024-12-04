package ru.otus.java.basic.homeworks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class PhoneBook {
    private TreeSet<RecordPhoneBook> phoneBookTreeSet;

    public PhoneBook(TreeSet<RecordPhoneBook> phoneBookTreeSet) {
        this.phoneBookTreeSet = phoneBookTreeSet;
    }

    void add(String phone, String name) {
        phoneBookTreeSet.add(new RecordPhoneBook(phone, name));
    }

    ArrayList<String> find(String name) {

        ArrayList<String> listPhone = new ArrayList<>();
        for (RecordPhoneBook phoneBook : phoneBookTreeSet) {
            if (phoneBook.getName().equals(name)) {
                listPhone.add(phoneBook.getPhone());
            }
        }
        return listPhone;
    }

    boolean containsPhoneNumber(String phone) {
        for (RecordPhoneBook phoneBook : this.phoneBookTreeSet) {
            if (phoneBook.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    void print() {
        for (RecordPhoneBook phoneBook : phoneBookTreeSet) {
            System.out.println("номер телефона: " + phoneBook.getPhone() + "    ФИО: " + phoneBook.getName());
        }
    }

}
