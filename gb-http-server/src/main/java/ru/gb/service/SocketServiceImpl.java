package ru.gb.service;

import ru.gb.logger.Logger;
import ru.gb.logger.LoggerFactory;
import ru.gb.logger.LoggerTypes;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Deque;
import java.util.LinkedList;

class SocketServiceImpl implements SocketService {

    private static final Logger logger = LoggerFactory.create(LoggerTypes.IMPROVED_CONSOLE);
    private final Socket socket;

    SocketServiceImpl(Socket socket) {
        this.socket = socket;
    }

    public Deque<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            while (!input.ready()) ;
            Deque<String> request = new LinkedList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(String rawResponse) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(rawResponse);
            output.flush();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }


    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
        logger.info("Client disconnected!");
    }
}
