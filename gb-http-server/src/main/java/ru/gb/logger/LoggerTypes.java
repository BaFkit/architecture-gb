package ru.gb.logger;

public enum LoggerTypes {

    CONSOLE("CONSOLE"),
    IMPROVED_CONSOLE("IMPROVED_CONSOLE"),
    FILE("FILE");

    final String type;

    LoggerTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
