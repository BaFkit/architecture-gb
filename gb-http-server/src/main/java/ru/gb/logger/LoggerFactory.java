package ru.gb.logger;

public class LoggerFactory {

    public static Logger create(String loggerType) {
        switch (loggerType) {
            case "CONSOLE":
                return new ConsoleLogger();
            case "FILE":
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
