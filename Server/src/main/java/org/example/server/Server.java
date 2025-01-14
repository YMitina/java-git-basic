package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private int port;
    private List<ClientHandler> clients;
    private AuthenticatedProvider authenticatedProvider;

    public Server(int port) {
        this.port = port;
        clients = new CopyOnWriteArrayList<>();
        authenticatedProvider = new InMemoryAuthenticatedProvider(this);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            authenticatedProvider.initialize();
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, this);
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
        for (ClientHandler c : clients) {
            c.sendMsg(message);
        }
    }

    public void personalMessage(String userSender, String message) {
        String[] wordsMessage = message.split(" ", 3);
        for (ClientHandler c : clients) {
            if (wordsMessage[0].equals("/w") && wordsMessage.length == 3) {
                if (c.getUsername().equals(wordsMessage[1]) || c.getUsername().equals(userSender)) {
                    c.sendMsg(userSender + " : " + wordsMessage[2]);
                }
            }
        }
    }
    public boolean isUsernameBusy(String usedrname) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(usedrname)) {
                return true;
            }
        }
        return false;
    }

    public AuthenticatedProvider getAuthenticatedProvider() {
        return authenticatedProvider;
    }
    public ClientHandler kickUser(String username, String loginKilk) {
        for (ClientHandler c : clients) {
            if (c.getUsername().equals(loginKilk)) {
                c.sendMsg("Админ " + username + " исключил Вас из чата принудительно!");
                c.sendMsg("/kickok");
                return c;
            }
        }
        return null;
    }

}
