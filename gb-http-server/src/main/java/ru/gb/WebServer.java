package ru.gb;

import ru.gb.config.Config;
import ru.gb.config.ConfigFactory;
import ru.gb.handler.AnnotatedHandlerFactory;
import ru.gb.handler.RequestHandler;
import ru.gb.logger.LoggerTypes;
import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;
import ru.gb.service.*;

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
                      SocketService socketService = SocketServiceFactory.createSocketService(serverSocket.accept());
                      ResponseSerializer responseSerializer = ResponseSerializerFactory.createResponseSerializer();
                new Thread(new RequestHandler(
                        socketService,
                        RequestParserFactory.createRequestParser(),
                        AnnotatedHandlerFactory.create(socketService, responseSerializer, config))
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
