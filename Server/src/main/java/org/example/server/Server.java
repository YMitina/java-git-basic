package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientHandler> clients;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(socket, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
        broadcastMessage("Из чата вышел: " + clientHandler.getUsername());
    }

    public void broadcastMessage(String message) {
        String[] userMessage = message.split(" ", 3);
        String[] wordsMessage = userMessage[2].split(" ", 3);
        for (ClientHandler c : clients) {
            if (wordsMessage[0].equals("/w") && wordsMessage.length == 3) {
                if (c.getUsername().equals(wordsMessage[1]) || c.getUsername().equals(userMessage[0])) {
                    c.sendMsg(userMessage[0] + " : " + wordsMessage[2]);
                }
            } else {
                c.sendMsg(message);
            }

        }
    }
}
