package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {


        File file = new File(".");
        System.out.println("file.listFiles() = " + Arrays.toString(file.listFiles()));

        System.out.println();

        System.out.println("Доступные текстовые файлы с расширением txt:");
        for (File files : file.listFiles()) {
            if (files.getName().toString().indexOf(".txt") != -1) {
                System.out.println(files.getName().toString());
            }
        }

        System.out.println();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла из доступных выше, для просмотра его содержимого:");
        String nameFile = scanner.nextLine();

        System.out.println();

        System.out.println("Содержимое файла " + nameFile + ":");
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(nameFile))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("Введите строку для записи в файл:");
        String newStringFile = scanner.nextLine();
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(nameFile, true))) {
            byte[] buffer = newStringFile.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < buffer.length; i++) {
                out.write(buffer[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        System.out.println("Содержимое файла " + nameFile.toString() + " после добавления в конец файла:");
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(nameFile))) {
            int n = in.read();
            while (n != -1) {
                System.out.print((char) n);
                n = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

    }
}
