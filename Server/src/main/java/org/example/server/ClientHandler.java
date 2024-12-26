package org.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    private String username;


    public ClientHandler(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());

        new Thread(() -> {
            try {
                System.out.println("Клиент подключился на порту: " + socket.getPort());
                //цикл аутентификации
                while (true) {
                    sendMsg("Для начала работы надо пройти аутентификацию. Формат команды /auth login password \n" +
                            "или регистрацию. Формат команды /reg login password username ");

                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        // /auth login password
                        if (message.startsWith("/auth ")) {
                            String[] element = message.split(" ");
                            if (element.length != 3) {
                                sendMsg("Неверный формат команды /auth");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .authenticate(this, element[1], element[2])) {
                                break;
                            }
                        }
                        // /reg login password username
                        if (message.startsWith("/reg ")) {
                            String[] element = message.split(" ");
                            if (element.length != 4) {
                                sendMsg("Неверный формат команды /reg");
                                continue;
                            }
                            if (server.getAuthenticatedProvider()
                                    .registration(this, element[1], element[2], element[3])) {
                                break;
                            }
                        }

                    }
                }

                //цикл работы
                while (true) {
                    String message = in.readUTF();
                    if (message.startsWith("/")) {
                        // /kick username
                        if (message.startsWith("/kick ")) {
                            String[] element = message.split(" ");
                            if (element.length != 2) {
                                sendMsg("Неверный формат команды /kick");
                            } else {
                                if (server.getAuthenticatedProvider().isAdmin(username)) {
                                   ClientHandler clientHandler = server.kickUser(username, element[1]);
                                   if (clientHandler == null) {
                                       sendMsg("Пользователь с именем " + element[1] + "не найден!");
                                   }else
                                   {
                                       sendMsg("Вы удалили пользователя " + element[1]);

                                   }
                                } else {
                                    sendMsg("У Вас недостаточно прав. Удалить из чата пользователя может только админ!");
                                }
                            }
                            continue;
                        }
                        if (message.startsWith("/w")) {
                            server.personalMessage(username, message);
                        } else if (message.equalsIgnoreCase("/exit")) {
                            sendMsg("/exitok");
                            break;
                        }
                    } else {
                        server.broadcastMessage(username + " : " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
    }

    public void sendMsg(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
