package ru.gb.handler;

import ru.gb.config.Config;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;
import ru.gb.domain.ResponseCode;
import ru.gb.service.ResponseSerializer;
import ru.gb.service.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetMethodHandler extends MethodHandler{

    @Handler(order = 0)
    public GetMethodHandler(String method, MethodHandler next, ResponseSerializer responseSerializer, SocketService socketService, Config config) {
        super(method, next, responseSerializer, socketService, config);
    }

    @Override
    protected HttpResponse handleInternal(HttpRequest httpRequest) {
        Path path = Paths.get(config.getWWW(), httpRequest.getPath());

        if (!Files.exists(path)) {
            return HttpResponse.createBuilder()
                    .withStatusCode(ResponseCode.NOT_FOUND)
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>File not found!</h1>")
                    .build();
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return HttpResponse.createBuilder()
                .withStatusCode(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(sb.toString())
                .build();
    }
}
