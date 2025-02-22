package ru.otus.java.basic.homeworks.processors;

import ru.otus.java.basic.homeworks.HttpRequest;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.IOException;
import java.io.OutputStream;

public interface RequestProcessor {
    void execute(HttpRequest request, OutputStream output) throws IOException;
}
