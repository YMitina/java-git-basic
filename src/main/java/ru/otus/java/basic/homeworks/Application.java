package ru.otus.java.basic.homeworks;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Application {
    public static void main(String[] args) {
        String filename= "2.txt";
        String text = "Hello World! Привет Мир! Прекрасный Мир!";
        String substrig = "Мир";
        createFile(filename, text );
        System.out.println("Количество подстроки '"  +  substrig +"' в файле '" + filename +   "' равно: " + findSubstrigToFile(filename, substrig));
    }
    public static void createFile(String filename, String text){
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filename))) {
            String strForWrite = text;;
            bufferedOutputStream.write(strForWrite.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public static int findSubstrigToFile(String filename, String substrig){
            try (FileInputStream fileInputStream = new FileInputStream(filename)) {
                byte[] buffer = fileInputStream.readAllBytes();
                String data = new String(buffer, StandardCharsets.UTF_8);
                    int count = 0;
                    for (int idx = 0; (idx =data.indexOf(substrig, idx)) != -1; idx += substrig.length()) {
                        count++;
                    }
                    return count;
                } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
