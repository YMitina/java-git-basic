package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Default500Processor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        File file = new File("./src/main/resources/500.png");
        try {
            if (!file.exists()) {
                HttpResponse r = new HttpResponse("500", "Internal Server Error", "text/html", "500 response page does not exist!", 0);
                output.write(r.getHttpResponse().getBytes());
                return;
            }
            HttpResponse r = new HttpResponse("500", "Internal Server Error", "", "", Files.readAllBytes(file.toPath()).length);
            output.write(r.getHttpResponse().getBytes());
            output.write(Files.readAllBytes(file.toPath()));
            System.out.println(r.getHttpResponse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
