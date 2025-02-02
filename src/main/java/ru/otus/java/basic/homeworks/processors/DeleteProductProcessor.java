package ru.otus.java.basic.homeworks.processors;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpServer;
import ru.otus.java.basic.homeworks.application.Product;
import ru.otus.java.basic.homeworks.application.ProductsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DeleteProductProcessor implements RequestProcessor {
    private static final Logger LOGGER = LogManager.getLogger(DeleteProductProcessor.class);
    private ProductsService productsService;

    public DeleteProductProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String result = String.valueOf(' ');
        if (request.containsParameter("id")) {
            Long id = Long.parseLong(request.getParameter("id"));
            productsService.deleteProductById(id);
            result = "DELETE";
        } else {
            productsService.deleteAllProducts();
            result = "DELETE ALL";
        }
        String response = "" +
                "HTTP/1.1 200 OK " + result + " \r\n" +
                "Content-Type: application/json\r\n" +
                "\r\n";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}