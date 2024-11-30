package ru.otus.java.basic.homeworks;

import java.util.Objects;

public class PhoneBook {
    private String phone;
    private String name;

    public PhoneBook(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void getPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook = (PhoneBook) o;
        return Objects.equals(name, this.name) && Objects.equals(phone, this.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, name);
    }
    public String toString(String phone, String name) {
        return "PhoneBook{" +
                "phone=" + phone +
                ", name='" + name + '\'' +
                '}';
    }

}
