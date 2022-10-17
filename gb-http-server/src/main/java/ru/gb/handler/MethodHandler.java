package ru.gb.handler;

import ru.gb.config.Config;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;
import ru.gb.domain.ResponseCode;
import ru.gb.service.ResponseSerializer;
import ru.gb.service.SocketService;

public abstract class MethodHandler {

    private final String method;
    private final MethodHandler next;

    protected final ResponseSerializer responseSerializer;
    protected final SocketService socketService;
    protected final Config config;

    public MethodHandler(String method, MethodHandler next, ResponseSerializer responseSerializer, SocketService socketService, Config config) {
        this.method = method;
        this.next = next;
        this.responseSerializer = responseSerializer;
        this.socketService = socketService;
        this.config = config;
    }

    public void handle(HttpRequest request) {
        HttpResponse httpResponse;
        if (method.equals(request.getMethod())) {
            httpResponse = handleInternal(request);
        } else if (next != null) {
            next.handle(request);
            return;
        } else {
            httpResponse = HttpResponse.createBuilder()
                    .withStatusCode(ResponseCode.METHOD_NOT_ALLOWED)
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Method not supported</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(httpResponse);
        socketService.writeResponse(rawResponse);
    }

    protected abstract HttpResponse handleInternal(HttpRequest httpRequest);
}
