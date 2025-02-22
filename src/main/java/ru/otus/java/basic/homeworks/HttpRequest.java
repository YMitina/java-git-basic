package ru.otus.java.basic.homeworks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger LOGGER = LogManager.getLogger(HttpRequest.class);
    private HttpMethod method;
    private String uri;
    private Map<String, String> parameters;
    private Map<String, String> headers;
    private String file;

    private Exception errorCause;

    public Exception getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(Exception errorCause) {
        this.errorCause = errorCause;
    }

    public String getUri() {
        return uri;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getRoutingKey() {
        return method + " " + uri; // 'GET /items', 'POST /items'
    }

    public HttpRequest(BufferedReader reader, String firstLine) throws IOException {
        parse(reader, firstLine);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public boolean containsParameter(String key) {
        return parameters.containsKey(key);
    }

    private void parse(BufferedReader reader, String firstLine) throws IOException {
        String flag = "FIRST_STRING";
        String line = firstLine;
        ParseLine parseLine = new ParseLine();
        this.parameters = new HashMap<>();
        this.headers = new HashMap<>();
        try {
            while (!line.isEmpty()) {
                if (flag.equals("FIRST_STRING")) {
                    this.method = HttpMethod.valueOf(parseLine.searchToSymbol(line, Symbols.SPACE.getValue(), true));
                    this.uri = parseLine.searchToSymbolsOr(line, Symbols.QUESTION.getValue(), Symbols.SPACE.getValue(), false);
                    if (parseLine.getCurrentSymbol() == Symbols.QUESTION.getValue()) {
                        do {
                            parameters.put(parseLine.searchToSymbol(line, Symbols.EQUELS.getValue(), false),
                                           parseLine.searchToSymbolsOr(line, Symbols.AND.getValue(), Symbols.SPACE.getValue(), false));
                        } while (parseLine.getCurrentSymbol() == Symbols.AND.getValue());
                    }
                    if (parseLine.getCurrentSymbol() == Symbols.SPACE.getValue()) {
                        flag = "HEADERS";
                        line = reader.readLine();
                    }
                }
                if (flag.equals("HEADERS")) {
                        headers.put(parseLine.searchToSymbol(line, Symbols.TWO_POINTS.getValue(), true), parseLine.searchToEnd(line));
                        line = reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (uri.contains(".") && !uri.contains("/favicon.ico")) {
                this.file = uri;
                this.uri = "/file";
            }
        }
    }

    public void infoPrint() {
        if (!uri.equals("/favicon.ico")) {
            LOGGER.info("Подключился новый клиент");
            LOGGER.info("METHOD: " + method);
            LOGGER.info("URI: " + uri);
            LOGGER.info("FILE: " + file);
            LOGGER.info("PARAMETERS: " + parameters);
            LOGGER.info("HEADERS: " + headers);
            LOGGER.info("----------------------------------------------------");
        }
    }
}
