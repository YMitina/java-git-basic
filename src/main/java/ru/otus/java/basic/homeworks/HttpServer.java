package ru.otus.java.basic.homeworks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworks.application.DatabaseProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final Logger LOGGER = LogManager.getLogger(HttpServer.class);
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService serv;
    private DatabaseProcessor databaseProcessor;

    public HttpServer(int port) {
        this.port = port;
        this.serv = Executors.newFixedThreadPool(2);
        databaseProcessor = new DatabaseProcessor();
        databaseProcessor.initialize();
        this.dispatcher = new Dispatcher(databaseProcessor);
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Сервер запущен на порту: " + port);

            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                serv.execute(() -> {
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                String firstLine = reader.readLine();
                                if (firstLine != null) {
                                    HttpRequest request = new HttpRequest(reader, firstLine);
                                    request.infoPrint();
                                    dispatcher.execute(request, socket.getOutputStream());
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } finally {
                                try {
                                    socket.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            databaseProcessor.close();
            serv.shutdown();
        }
    }
}


