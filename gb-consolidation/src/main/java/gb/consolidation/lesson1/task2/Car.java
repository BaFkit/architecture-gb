package gb.consolidation.lesson1.task2;

abstract class Car {

    protected Engine engine;
    protected String color;
    protected String name;

    protected void start() {
        System.out.println("Car starting");
    }

    abstract void open();

    abstract void move();

    protected void stop() {
        System.out.println("Car is stop");
    }

    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
