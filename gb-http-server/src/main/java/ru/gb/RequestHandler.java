package ru.gb;

import ru.gb.config.Config;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;
import ru.gb.domain.ResponseCode;
import ru.gb.service.RequestParser;
import ru.gb.service.ResponseSerializer;
import ru.gb.service.SocketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;

public class RequestHandler implements Runnable {

    Config config;
    RequestParser requestParser;
    ResponseSerializer responseSerializer;
    SocketService socketService;

    public RequestHandler(Config config,
                          SocketService socketService,
                          RequestParser requestParser,
                          ResponseSerializer responseSerializer
    ) {
        this.config = config;
        this.socketService = socketService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {
        Deque<String> rawRequest = socketService.readRequest();
        HttpRequest httpRequest = requestParser.parse(rawRequest);

        Path path = Paths.get(config.getWWW(), httpRequest.getPath());

        if (!Files.exists(path)) {
            HttpResponse httpResponse = HttpResponse.createBuilder()
                    .withStatusCode(ResponseCode.NOT_FOUND)
                    .withHeader("Content-Type", "text/html; charset=utf-8")
                    .withBody("<h1>Файл не найден!</h1>")
                    .build();

            String rawResponse = responseSerializer.serialize(httpResponse);
            socketService.writeResponse(rawResponse);
            return;
        }

        StringBuilder sb = new StringBuilder();
        try {
            Files.readAllLines(path).forEach(sb::append);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HttpResponse httpResponse = HttpResponse.createBuilder()
                .withStatusCode(ResponseCode.OK)
                .withHeader("Content-Type", "text/html; charset=utf-8")
                .withBody(sb.toString())
                .build();
        String rawResponse = responseSerializer.serialize(httpResponse);
        socketService.writeResponse(rawResponse);

        try {
            socketService.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
