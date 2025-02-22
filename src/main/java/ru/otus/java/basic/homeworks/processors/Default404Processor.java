package ru.otus.java.basic.homeworks.processors;

import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.util.StreamUtils;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

public class Default404Processor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        File file = new File("./src/main/resources/404.png");
        try {
            if (!file.exists()) {
                HttpResponse r = new HttpResponse("404", "Not Found", "text/html", "404 response page does not exist!", 0);
                output.write(r.getHttpResponse().getBytes());
                return;
            }
            HttpResponse r = new HttpResponse("404", "Not Found", "", "", Files.readAllBytes(file.toPath()).length);
            output.write(r.getHttpResponse().getBytes());
            output.write(Files.readAllBytes(file.toPath()));
            System.out.println(r.getHttpResponse());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
