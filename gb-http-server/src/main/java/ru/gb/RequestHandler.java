package ru.gb;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler implements Runnable {

    Config config;
    InputHandler inputHandler;
    OutputHandler outputHandler;
    SocketService socketService;

    public RequestHandler(Config config, SocketService socketService) {
        this.config = config;
        this.socketService = socketService;
        inputHandler = new InputHandler(socketService);
        outputHandler = new OutputHandler(socketService);
    }

    @Override
    public void run() {

        String[] parts = new String[0];
        try {
            parts = inputHandler.getInput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Path path = Paths.get(config.getWWW(), parts[1]);

        outputHandler.output(path);

        try {
            socketService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
