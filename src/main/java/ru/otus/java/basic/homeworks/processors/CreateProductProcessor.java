package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateProductProcessor implements RequestProcessor {
    private DatabaseProcessor databaseProcessor;

    public CreateProductProcessor(DatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (request.containsParameter("id") && request.containsParameter("title")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title").toString();
            databaseProcessor.createNewProduct(id, title);
        }
        HttpResponse r = new HttpResponse("201", "OK Created", "text/html", "", 0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));

    }
}
