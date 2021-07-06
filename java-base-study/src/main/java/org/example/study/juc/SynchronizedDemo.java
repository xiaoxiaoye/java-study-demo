package org.example.study.juc;

import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    public synchronized void f1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(500);
    }

    public synchronized void f2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1000);
    }

    public static void main(String[] args) {
        final SynchronizedDemo demo = new SynchronizedDemo();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.f1();
                } catch (InterruptedException e) {

                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    demo.f2();
                } catch (InterruptedException e) {

                }

            }
        });

        try {
            long start = System.currentTimeMillis();
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("cost: " + (System.currentTimeMillis() - start));
        } catch (InterruptedException e) {

        }
    }
}
