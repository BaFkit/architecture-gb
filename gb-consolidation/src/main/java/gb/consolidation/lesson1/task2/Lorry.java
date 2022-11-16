package gb.consolidation.lesson1.task2;

public class Lorry extends Car{

    @Override
    void open() {
        System.out.println("Lorry is open");
    }

    @Override
    void move() {
        System.out.println("Lorry is moving");
    }
}
