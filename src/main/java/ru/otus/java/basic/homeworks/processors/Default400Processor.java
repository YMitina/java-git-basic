package ru.otus.java.basic.homeworks.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.BadRequestException;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;
import ru.otus.java.basic.homeworks.application.ErrorDto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Default400Processor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        ErrorDto errorDto = new ErrorDto(
                ((BadRequestException) request.getErrorCause()).getCode(),
                ((BadRequestException) request.getErrorCause()).getDescription()
        );
        Gson gson = new Gson();
        String jsonError = gson.toJson(errorDto);
        HttpResponse r = new HttpResponse("400", "Bad Request", "application/json", jsonError, 0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));

    }

}

