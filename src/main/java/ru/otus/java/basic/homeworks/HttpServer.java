package ru.otus.java.basic.homeworks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final Logger LOGGER =  LogManager.getLogger(HttpServer.class);
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService serv;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
        this.serv = Executors.newFixedThreadPool(5);
    }
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
           // System.out.println("Сервер запущен на порту: " + port);
            LOGGER.info("Сервер запущен на порту: " + port);
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                serv.execute(() -> {
                            byte[] buffer = new byte[8192];
                            try {
                                int n = socket.getInputStream().read(buffer);
                                if (n>0) {
                                    LOGGER.info("Подключился новый клиент");
                                    HttpRequest request = new HttpRequest(new String(buffer, 0, n));
                                    request.info(true);
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
            serv.shutdown();
        } finally {
            serv.shutdown();
        }
    }
}


