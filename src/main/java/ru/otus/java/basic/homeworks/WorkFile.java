package ru.otus.java.basic.homeworks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WorkFile {

    public void viewFilesInCurrentDirectory() {
        File file = new File(".");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите расширение текстовых файлов для поиска в текущем каталоге:");
        String extension = scanner.nextLine();
        System.out.println("Доступные текстовые файлы с расширением " + extension.toString() + ":");
        for (File files : file.listFiles()) {
            if (files.getName().toString().indexOf("." + extension.toString()) != -1) {
                System.out.println(files.getName().toString());
            }
        }
        System.out.println();
    }

    public void printOutputContentsToFile(String nameFile)
    {
        System.out.println("Содержимое файла " + nameFile.toString() + ":");
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
        System.out.println();
    }

    public void addTextInFile(String nameFile, String newStringFile) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(nameFile, true))) {
            byte[] buffer = newStringFile.getBytes(StandardCharsets.UTF_8);
            for (int i = 0; i < buffer.length; i++) {
                out.write(buffer[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
