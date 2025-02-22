package ru.otus.java.basic.homeworks;


import java.io.IOException;

public class Application {
     public static void main(String[] args) {
           try {
                new HttpServer(8189).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
