package ru.gb;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {

    public static void main(String[] args) {
        Config config = new Config("www/index.html", 8089);

        try (ServerSocket serverSocket = new ServerSocket(config.getPORT())) {
            System.out.println("Server started!");

            while (true) {
                SocketService socketService = new SocketService(serverSocket.accept());
                System.out.println("New client connected!");

                new Thread(new RequestHandler(config, socketService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
