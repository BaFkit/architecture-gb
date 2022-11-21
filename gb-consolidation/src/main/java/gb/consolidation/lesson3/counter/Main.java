package gb.consolidation.lesson3.counter;

public class Main {

    public static void main(String[] args) {
        try {
            Counter.startCounter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
