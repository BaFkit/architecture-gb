package gb.consolidation.lesson1.task2;

public class Engine {

    private final String model;

    private final int volume;

    public Engine(String model, int volume) {
        this.model = model;
        this.volume = volume;
    }

    public String getModel() {
        return model;
    }

    public int getVolume() {
        return volume;
    }
}
