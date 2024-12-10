package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        WorkFile workFile = new WorkFile();

        workFile.viewFilesInCurrentDirectory();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла из доступных выше, для просмотра его содержимого:");
        String nameFile = scanner.nextLine();
        workFile.printOutputContentsToFile(nameFile);

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Введите строку для добавления в конец файла:");
        String newStringFile = scanner2.nextLine();
        workFile.addTextInFile(nameFile, newStringFile) ;
        workFile.printOutputContentsToFile(nameFile);
    }
}
