package gb.consolidation.lesson1.task2;

public class LightWeightCar extends Car{

    @Override
    void open() {
        System.out.println("LightWeightCar is open");
    }

    @Override
    void move() {
        System.out.println("LightWeightCar is moving");
    }
}
