package ru.otus.java.basic.homeworks.processors;

import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.BadRequestException;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.application.Product;
import ru.otus.java.basic.homeworks.application.ProductsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UpdateProductProcessor implements RequestProcessor {
    private ProductsService productsService;

    public UpdateProductProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String jsonResult = null;
        Gson gson = new Gson();
        if (request.containsParameter("id") && request.containsParameter("title")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String title = request.getParameter("title").toString();
            productsService.updateProductById(id,title );
        } else {
            throw new BadRequestException(
                    "VALIDATION_ERROR_MISSING_FIELD",
                    "Отсутствует или не верно указаны параметры запроса id/title"
            );
        }
        String response = "" +
                "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n" +
                jsonResult;
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}