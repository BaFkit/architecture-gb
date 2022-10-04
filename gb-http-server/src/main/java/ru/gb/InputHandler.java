package ru.gb;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHandler {

    private final SocketService socketService;

    public InputHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    public String[] getInput() throws IOException {
        BufferedReader input = socketService.getInput();

            while (!input.ready());
            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            System.out.println(firstLine);
            while (input.ready()) {
                System.out.println(input.readLine());
            }
            return parts;
        }
}
