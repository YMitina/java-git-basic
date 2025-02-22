package ru.otus.java.basic.homeworks.processors;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworks.HttpRequest;

import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


public class DeleteProductProcessor implements RequestProcessor {
    private static final Logger LOGGER = LogManager.getLogger(DeleteProductProcessor.class);
    private DatabaseProcessor databaseProcessor;

    public DeleteProductProcessor( DatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String result = String.valueOf(' ');
        if (request.containsParameter("id")) {
            Long id = Long.parseLong(request.getParameter("id"));
            databaseProcessor.deleteProductById(id);
            result = "DELETE";
        } else {
            databaseProcessor.deleteAllProducts();
            result = "DELETE ALL";
        }
        HttpResponse r = new HttpResponse("200", "OK " +  result, "text/html","",0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
    }
}