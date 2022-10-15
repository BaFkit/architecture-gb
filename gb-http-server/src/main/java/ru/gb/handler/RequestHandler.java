package ru.gb.handler;

import ru.gb.domain.HttpRequest;
import ru.gb.service.RequestParser;
import ru.gb.service.SocketService;

import java.io.IOException;
import java.util.Deque;

public class RequestHandler implements Runnable {

    private final RequestParser requestParser;
    private final MethodHandler methodHandler;
    private final SocketService socketService;

    public RequestHandler(
                          SocketService socketService,
                          RequestParser requestParser,
                          MethodHandler methodHandler
    ) {
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.methodHandler = methodHandler;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(rawRequest);

        methodHandler.handle(httpRequest);

        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
