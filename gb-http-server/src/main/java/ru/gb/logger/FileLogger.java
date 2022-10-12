package ru.gb.logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{

    @Override
    public void info(String msg) {
        try(FileWriter writer = new FileWriter("gb-http-server/src/main/java/ru/gb/logger/log.txt", true)) {

            writer.write(msg);
            writer.append('\n');
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
