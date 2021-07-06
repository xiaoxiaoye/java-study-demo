package org.example.study.juc;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    private ReentrantLock lock = new ReentrantLock();

    private int i;

    public void add() {
        lock.lock();
        try {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
            Thread.sleep(1000000);
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        final LockDemo demo = new LockDemo();
        Thread thread1 = new Thread(()-> demo.add());
        Thread thread2 = new Thread(()-> demo.add());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(demo.i);
    }
}
