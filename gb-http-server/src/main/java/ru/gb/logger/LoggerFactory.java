package ru.gb.logger;

public class LoggerFactory {

    public static Logger create(LoggerTypes loggerType) {

        switch (loggerType) {
            case CONSOLE:
                return new ConsoleLogger();
            case IMPROVED_CONSOLE:
                return new ImprovedConsoleLogger(new ConsoleLogger());
            case FILE:
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

}
