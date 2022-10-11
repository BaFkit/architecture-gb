package ru.gb;

import ru.gb.logger.ConsoleLogger;
import ru.gb.logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {

    private static final Logger logger = new ConsoleLogger();

    public static void main(String[] args) {
        Config config = new Config("www/index.html", 8089);

        try (ServerSocket serverSocket = new ServerSocket(config.getPORT())) {
            logger.info("Server started!");

            while (true) {
                SocketService socketService = new SocketService(serverSocket.accept());
                logger.info("New client connected!");

                new Thread(new RequestHandler(config, socketService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
