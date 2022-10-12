package ru.gb.config;

class ConfigFromCli implements Config{

    private final String WWW;

    private final int PORT;

    public ConfigFromCli(String[] args) {
        if (args.length < 2) {
            throw new IllegalStateException("No parameters specified");
        }
        this.WWW = args[0];
        this.PORT = Integer.parseInt(args[1]);

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
