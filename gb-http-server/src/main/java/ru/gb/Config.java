package ru.gb;

public class Config {

    private final String WWW;
    private final int PORT;

    public String getWWW() {
        return WWW;
    }

    public int getPORT() {
        return PORT;
    }

    public Config(String WWW, int PORT) {
        this.WWW = WWW;
        this.PORT = PORT;
    }
}
