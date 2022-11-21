package gb.consolidation.lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    static int count = 0;

    public static void startCounter() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(()->{
            for(int i = 0; i < 500000; i++){
                lock.lock();
                count ++;
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(()->{
            for(int i = 0; i < 500000; i++){
                lock.lock();
                count ++;
                lock.unlock();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(count);
    }

}
