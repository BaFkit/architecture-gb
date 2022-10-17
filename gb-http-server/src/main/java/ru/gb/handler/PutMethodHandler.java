package ru.gb.handler;

import ru.gb.config.Config;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;
import ru.gb.domain.ResponseCode;
import ru.gb.service.ResponseSerializer;
import ru.gb.service.SocketService;

@Handler(order = 2)
public class PutMethodHandler extends MethodHandler{
    public PutMethodHandler(String method, MethodHandler next, ResponseSerializer responseSerializer, SocketService socketService, Config config) {
        super(method, next, responseSerializer, socketService, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest httpRequest) {
        return HttpResponse.createBuilder()
                .withStatusCode(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody("<h1>Put method handled</h1>")
                .build();
    }
}
