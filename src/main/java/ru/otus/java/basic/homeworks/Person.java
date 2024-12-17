package ru.otus.java.basic.homeworks;


public class Person {
    String name;
    Post position;
    Long id;


    public Person(String name, Long id) {
        this.name = name;
        this.id = id;
        this.position = Post.random();
    }

    public Long getId() {
        return id;
    }

    public void print() {
        System.out.println("ФИО: " + name + ", Должность: " + position + ", Табельный номер: " + id);
    }

}