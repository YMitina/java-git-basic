package ru.otus.java.basic.homeworks.processors;


import ru.otus.java.basic.homeworks.BadRequestException;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UpdateProductProcessor implements RequestProcessor {
    private DatabaseProcessor databaseProcessor;

    public UpdateProductProcessor(DatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (request.containsParameter("id") && request.containsParameter("title")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title").toString();
            databaseProcessor.updateProductById(id,title);
        } else {
            throw new BadRequestException(
                    "VALIDATION_ERROR_MISSING_FIELD",
                    "Отсутствует или не верно указаны параметры запроса id/title"
            );
        }
        HttpResponse r = new HttpResponse("200", "OK UPDATE", "text/html",  "", 0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
    }
}