package ru.gb;

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
    }

    @Override
    public void run() {

        Path path = Paths.get(config.getWWW(), requestParser.parse(socketService.readRequest()).getPath());

        if (!Files.exists(path)) {
            HttpResponse httpResponse = new HttpResponse(404, "<h1>Файл не найден!</h1>\n");
            socketService.writeResponse(responseSerializer.serialize(httpResponse), new StringReader(httpResponse.getBody()));
            return;
        }

        try {
            HttpResponse httpResponse = new HttpResponse(200, Files.newBufferedReader(path).toString());
            socketService.writeResponse(responseSerializer.serialize(httpResponse), new StringReader(httpResponse.getBody()));
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
