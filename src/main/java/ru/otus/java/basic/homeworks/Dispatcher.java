package ru.otus.java.basic.homeworks;

import ru.otus.java.basic.homeworks.application.DatabaseProcessor;
import ru.otus.java.basic.homeworks.processors.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private Default400Processor default400Processor;
    private Default404Processor default404Processor;
    private Default500Processor default500Processor;
    private DatabaseProcessor databaseProcessor;

    public Dispatcher(DatabaseProcessor databaseProcessor) {
        this.databaseProcessor = databaseProcessor;
        this.router = new HashMap<>();
        this.router.put("GET /file", new FileProcessor());
        this.router.put("GET /calc", new CalculatorProcessor());
        this.router.put("GET /welcome", new WelcomeProcessor());
        this.router.put("GET /products", new GetProductsProcessor(databaseProcessor));
        this.router.put("POST /products", new CreateProductProcessor(databaseProcessor));
        this.router.put("DELETE /products", new DeleteProductProcessor(databaseProcessor));
        this.router.put("PUT /products", new UpdateProductProcessor(databaseProcessor));
        this.default400Processor = new Default400Processor();
        this.default404Processor = new Default404Processor();
        this.default500Processor = new Default500Processor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
            try {
                if (!router.containsKey(request.getRoutingKey())) {
                    default404Processor.execute(request, output);
                    return;
                }
                router.get(request.getRoutingKey()).execute(request, output);
            } catch (BadRequestException e) {
                e.printStackTrace();
                request.setErrorCause(e);
                default400Processor.execute(request, output);
            } catch (Exception e) {
                e.printStackTrace();
                default500Processor.execute(request, output);
            }
        }
}
