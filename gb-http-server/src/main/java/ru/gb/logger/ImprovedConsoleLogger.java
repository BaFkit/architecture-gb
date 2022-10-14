package ru.gb.logger;

import java.util.Calendar;

class ImprovedConsoleLogger implements Logger{

    public ImprovedConsoleLogger(Logger logger) {
    }

    @Override
    public void info(String msg) {
        System.out.println(Calendar.getInstance().getTime() + ": " + msg);
    }
}
