package ru.otus.java.basic.homeworks;

import java.nio.file.Files;

public class HttpResponse {
    private String status;
    private String description;
    private String contentType;
    private String text;
    private int contentLength;

    public HttpResponse(String status, String description, String contentType, String text, int contentLength) {

        this.status = status;
        this.description = description;
        this.contentType = contentType;
        this.text = text;
        this.contentLength = contentLength;

    }

    public String getHttpResponse() {
        if (contentType.equals("text/html")) {
            if (text.equals("")) {
                return "HTTP/1.1 " + status + " " + description + "\r\n" +
                        "Content-Type: " + contentType + "\r\n" +
                        "\r\n";
            }

            return "HTTP/1.1 " + status + " " + description + "\r\n" +
                    "Content-Type: " + contentType + "\r\n" +
                    "\r\n" +
                    "<html><body>" +
                    "<b1>" + text + "</b1>" + "</body></html>";
        }
        if (contentType.equals("application/json")) {
            return "HTTP/1.1 " + status + " " + description + "\r\n" +
                    "Content-Type: " + contentType + "\r\n" +
                    "\r\n" +
                    text;
        }
        return "HTTP/1.1 " + status + " " + description + "\r\n" +
                "ContentLength: " + contentLength + "\r\n" +
                "<header></header>\r\n" +
                "\r\n";
    }
}
