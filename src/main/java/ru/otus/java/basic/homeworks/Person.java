package ru.otus.java.basic.homeworks;


public class Person {
    private String name;
    private Post position;
    private Long id;


    public Person(String name, Long id) {
        this.name = name;
        this.id = id;
        this.position = Post.random();
    }

    public Long getId() {
        return id;
    }
    public Post getPosition() {
        return position;
    }

    public String toString() {
        return  "ФИО: " + name + ", Должность: " + position + ", Табельный номер: " + id;
    }

}