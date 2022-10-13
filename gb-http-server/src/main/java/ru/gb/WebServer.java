package ru.gb;

import ru.gb.config.Config;
import ru.gb.config.ConfigFactory;
import ru.gb.logger.LoggerTypes;
import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;
import ru.gb.service.RequestParserFactory;
import ru.gb.service.ResponseSerializerFactory;
import ru.gb.service.SocketServiceFactory;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {

    private static final Logger logger = LoggerFactory.create(LoggerTypes.IMPROVED_CONSOLE);

    public WebServer(String[] args) {
        Config config = ConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            logger.info("Server started at port: " + config.getPort());
            while (true) {
                logger.info("New client connected!");

                new Thread(new RequestHandler(
                        config,
                        SocketServiceFactory.createSocketService(serverSocket.accept()),
                        RequestParserFactory.createRequestParser(),
                        ResponseSerializerFactory.createResponseSerializer()))
                        .start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
