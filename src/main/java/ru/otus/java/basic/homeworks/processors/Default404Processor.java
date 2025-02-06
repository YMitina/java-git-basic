package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Default404Processor implements RequestProcessor {
    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String response = "" +
                "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><body>" +
               "<h1>"+"FOOL" +"</h1>"+
                "<img src=\"image2.png\" ></body></html>";
        output.write(response.getBytes(StandardCharsets.UTF_8));
        System.out.println(response);
    }
}
