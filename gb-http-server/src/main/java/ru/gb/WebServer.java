package ru.gb;

import ru.gb.config.Config;
import ru.gb.config.ConfigFactory;
import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {

    private static final Logger logger = LoggerFactory.create("CONSOLE");

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started at port: " + config.getPort());

            while (true) {
                SocketService socketService = SocketService.createSocketService(serverSocket.accept());
                logger.info("New client connected!");

                new Thread(new RequestHandler(config, socketService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
