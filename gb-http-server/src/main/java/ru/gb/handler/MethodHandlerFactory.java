package ru.gb.handler;

import ru.gb.config.Config;
import ru.gb.service.ResponseSerializer;
import ru.gb.service.SocketService;

public class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, Config config) {
        PutMethodHandler putMethodHandler = new PutMethodHandler("PUT", null, socketService, responseSerializer, config);
        PostMethodHandler postMethodHandler = new PostMethodHandler("POST", putMethodHandler, socketService, responseSerializer, config);
        return new GetMethodHandler("GET", postMethodHandler, socketService, responseSerializer, config);
    }
}
