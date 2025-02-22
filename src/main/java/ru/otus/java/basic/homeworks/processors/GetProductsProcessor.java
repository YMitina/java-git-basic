package ru.otus.java.basic.homeworks.processors;


import com.google.gson.Gson;
import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.HttpResponse;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;
import ru.otus.java.basic.homeworks.application.Product;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetProductsProcessor implements RequestProcessor {
    private DatabaseProcessor databaseProcessor;

    public GetProductsProcessor(DatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String jsonResult = null;
        Gson gson = new Gson();
        if (request.containsParameter("id")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Product product = databaseProcessor.getProductById(id);
            jsonResult = gson.toJson(product);
        } else {
            List<Product> products = databaseProcessor.getAllProducts();
            jsonResult = gson.toJson(products);
        }
        HttpResponse r = new HttpResponse("200", "OK", "application/json",  jsonResult, 0);
        output.write(r.getHttpResponse().getBytes(StandardCharsets.UTF_8));
    }
}
