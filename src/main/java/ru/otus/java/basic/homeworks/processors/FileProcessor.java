package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        System.out.println(" request.getFile()=" + request.getFile());
        File file = new File("./src/main/static" + request.getFile());

        if (!file.exists()) {
            HttpResponse r = new HttpResponse("404", "Not Found", "text/html", "NOT DATA FOUND FILE!", 0);
            output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
        } else {
            try {
                HttpResponse r = new HttpResponse("200", "OK GET FILE", "", "", Files.readAllBytes(file.toPath()).length);
                output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
                output.write(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                HttpResponse r = new HttpResponse("500", "Can not read file", "text/html", "CAN NOT DATA READ FILE!", 0);
                output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
            }
        }
    }

}