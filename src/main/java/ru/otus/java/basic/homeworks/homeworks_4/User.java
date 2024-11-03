package ru.otus.java.basic.homeworks.homeworks_4;

public class User {
   private String name;
   private String surname;
   private String patronymic;
   private int yearOfBirth;
   private String email;

   public User(String surname, String name, String patronymic, int yearOfBirth, String email){
       this.surname = surname;
       this.name = name;
       this.patronymic = patronymic;
       this.yearOfBirth = yearOfBirth;
       this.email = email;
   }
   public void printInfoUser(){
       System.out.println("ФИО: " + this.surname + " " + this.name + " "+ this.patronymic);
       System.out.println("Год рождения: " + this.yearOfBirth);
       System.out.println("e-mail: " + this.email);
   }
   public int getYearOfBirth(){
       return this.yearOfBirth;
   }

}
