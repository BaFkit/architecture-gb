package gb.consolidation.lesson3;

public class PingPongApp {

    private final Object monitor = new Object();
    private boolean state;

    public void play() {
        Thread thread1 = new Thread(this::printPing);
        Thread thread2 = new Thread(this::printPong);
        thread1.start();
        thread2.start();
    }

    private void printPing() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (state) {
                        monitor.wait();
                    }
                    System.out.println("Ping");
                    state = true;
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printPong() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 10; i++) {
                    while (!state) {
                        monitor.wait();
                    }
                    System.out.println("Pong");
                    state = false;
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
