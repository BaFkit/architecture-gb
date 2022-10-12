package ru.gb;

import ru.gb.config.Config;
import ru.gb.domain.HttpRequest;
import ru.gb.domain.HttpResponse;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    Config config;
    RequestParser requestParser;
    ResponseSerializer responseSerializer;
    SocketService socketService;

    public RequestHandler(Config config, SocketService socketService) {
        this.config = config;
        this.socketService = socketService;
        requestParser = HttpRequest.createBuilder().build();
        responseSerializer = HttpResponse.createBuilder().build();
    }

    @Override
    public void run() {

        Path path = Paths.get(config.getWWW(), requestParser.parse(socketService.readRequest()).getPath());

        if (!Files.exists(path)) {
            HttpResponse httpResponse = HttpResponse.createBuilder()
                            .withStatusCode(404)
                            .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                            .build();
            socketService.writeResponse(responseSerializer.serialize(httpResponse), httpResponse.getBody());
            return;
        }

        try {
            HttpResponse httpResponse = HttpResponse.createBuilder()
                    .withStatusCode(200)
                    .withBody(Files.newBufferedReader(path))
                    .build();
            socketService.writeResponse(responseSerializer.serialize(httpResponse), httpResponse.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            socketService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
