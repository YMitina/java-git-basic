package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WelcomeProcessor implements RequestProcessor {
   @Override
   public void execute(HttpRequest request, OutputStream output) throws IOException {
       HttpResponse r = new HttpResponse("200", "OK", "text/html",  "Welcome Page", 0);
       output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
    }
}
