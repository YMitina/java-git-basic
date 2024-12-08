package ru.otus.java.basic.homeworks;

import java.util.*;

public class PhoneBook {
    //private TreeSet<RecordPhoneBook> phoneBookTreeSet;

    private Map<String, Set<String>> phoneBook = new HashMap<>();

    public void add(String name, String phone) {
        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phone);
        } else {
            HashSet<String> hashSetPhone = new HashSet<String>();
            hashSetPhone.add(phone);
            phoneBook.put(name, hashSetPhone);
        }
    }

    public String find(String name) {
        return phoneBook.getOrDefault(name, Collections.singleton("ОТСУТСТВУЮТ!")).toString();
    }

    public boolean containsPhoneNumber(String phone) {
        for (Set<String> phones : phoneBook.values()) {
            if (phones.contains(phone)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for (Map.Entry<String, Set<String>> entery : phoneBook.entrySet()) {
            String name = entery.getKey();
            Set<String> phones = entery.getValue();
            System.out.println("ФИО: " + name + " номер телефона: " + phones);
        }
    }

}

