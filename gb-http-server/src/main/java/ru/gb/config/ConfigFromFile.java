package ru.gb.config;

import java.io.IOException;
import java.util.Properties;

class ConfigFromFile implements Config{

    private final String WWW;

    private final int PORT;

    public ConfigFromFile(String fileName) {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream(fileName));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        this.WWW = prop.getProperty("WWW");
        this.PORT = Integer.parseInt(prop.getProperty("PORT"));
    }

    @Override
    public String getWWW() {
        return this.WWW;
    }

    @Override
    public int getPort() {
        return this.PORT;
    }
}
