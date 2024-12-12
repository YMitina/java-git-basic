package ru.otus.java.basic.homeworks;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import static java.lang.Integer.valueOf;

public class Server {
    private static final List<ClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8181);
        System.out.println("SERVER APPLICATION RUN!");
        while (true) {
            Socket client = socket.accept();
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            System.out.println("Клиент с портом :" + client.getPort() + " подключился!");
            ClientHandler clientHandler = new ClientHandler(client, inputStream, outputStream);
            clientHandlers.add(clientHandler);
            String userInput = inputStream.readUTF();
            if (userInput.equals("exit")) {
                System.out.println("Клиент с портом :" + client.getPort() + " отключился!");
                client.close();
                continue;
            }
            System.out.println(userInput);
            System.out.println("client.getPort() = " + client.getPort());

            String resultOut = Calc(userInput);
            outputStream.writeUTF(resultOut);
            outputStream.flush();
            System.out.println("result = " + resultOut);
        }
    }

    private static String Calc(String userInput) {
        String[] words;
        try {
            words = userInput.split(",");

            if (words[2].equals("/") && words[1].equals("0")) {
                return "Попытка деления на ноль. Операция не выполнима!";
            }
            int number1;
            int number2;
            try {
                number1 = valueOf(words[0]);
                number2 = valueOf(words[1]);
            } catch (NumberFormatException e) {
                return "Неправильный формат чисел!";
            }

            System.out.println("Выполняем вычисления!");
            String example = words[0] + " " + words[2] + " " + words[1] + " = ";

            switch (words[2]) {
                case "+":
                    return example + (number1 + number2);
                case "-":
                    return example + (number1 - number2);
                case "*":
                    return example + (number1 * number2);
                case "/":
                    try {
                        return example + (number1 / number2);
                    } catch (ArithmeticException e) {
                        return "Попытка деления на ноль. Операция не выполнима!";
                    }
                default:
                    return "Указана недопустимая арифмитическая операция!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Некорректных формат данных.Необходимо ввести два числа и операцию через ЗАПЯТУЮ!";
        }


    }
}