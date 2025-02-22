package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.BadRequestException;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CalculatorProcessor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        if (!request.containsParameter("a")) {
            throw new BadRequestException(
                    "VALIDATION_ERROR_MISSING_FIELD",
                    "Отсутствует параметр запроса 'a'"
            );
        }
        if (!request.containsParameter("b")) {
            throw new BadRequestException(
                    "VALIDATION_ERROR_MISSING_FIELD",
                    "Отсутствует параметр запроса 'b'"
            );
        }

        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        String result = a + " + " + b + " = " + (a + b);
        HttpResponse r = new HttpResponse("200", "OK Calc", "text/html", result,0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));

    }
}

