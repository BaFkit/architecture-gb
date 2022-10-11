package ru.gb.logger;

class ConsoleLogger implements Logger{
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
