package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.homeworks_4.User;
import ru.otus.java.basic.homeworks.homeworks_4.Box;

import java.time.Year;


public class Application {
    public static void main(String[] args) {
      //  System.out.println("Hello world!");
      //  System.out.println("Привет мир!!!");
        //1
        User[] userArr = new User[10];
        userArr [0]= new User("Иванов", "Иван", "Иванович", 2000, "zzz1@mail.ru");
        userArr [1]= new User("Петров", "Петр", "Петрович", 1998, "zzz2@mail.ru");
        userArr [2]= new User("Сидоров", "Сидр", "Сидорович", 1999, "zzz3@mail.ru");
        userArr [3]= new User("Митина", "Юлия", "Ивановна", 2016, "zzz4@mail.ru");
        userArr [4]= new User("Казаков", "Иван", "Сергеевич", 1997, "zzz5@mail.ru");
        userArr [5]= new User("Пушкин", "Александ", "Сергеевич", 1799, "zzz6@mail.ru");
        userArr [6]= new User("Толстой", "Лев", "Николаевич", 1828, "zzz7@mail.ru");
        userArr [7]= new User("Бунин", "Иван", "Алексеевич", 1870, "zzz8@mail.ru");
        userArr [8]= new User("Гоголь", "Николай", "Васильевич", 1809, "zzz9@mail.ru");
        userArr [9]= new User("Фет", "Афанасий", "Афанасьевич", 1820, "zzz10@mail.ru");
        int currentYear = Year.now().getValue();
        for (int i = 0; i <  userArr.length; i++) {
            if ((currentYear - userArr[i].getYearOfBirth()) > 40){
                userArr[i].printInfoUser();
                System.out.println();
            }

        }
        Box box1 = new Box(10,20,30,"BLACK");
        box1.printInfoBox();
        box1.setColor("PINK");
        box1.printInfoBox();
        box1.close();
        box1.putItem("Мяч");
        box1.open();
        box1.deleteItem();
        box1.putItem("Мяч");
        box1.close();
        box1.deleteItem();
        box1.open();
        box1.deleteItem();



    }
}
