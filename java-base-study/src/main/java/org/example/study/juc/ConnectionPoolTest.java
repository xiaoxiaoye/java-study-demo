package org.example.study.juc;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadCount = 10;
        int count = 20;
        AtomicInteger get = new AtomicInteger();
        AtomicInteger noGet = new AtomicInteger();
        end = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, get, noGet), "ConnectionRunnerThread#" + i);
            thread.start();
        }

        start.countDown();
        end.await();

        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got connection: " + get);
        System.out.println("no get connection: " + noGet);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger get;
        AtomicInteger noGet;

        public ConnectionRunner(int count, AtomicInteger get, AtomicInteger noGet) {
            this.count = count;
            this.get = get;
            this.noGet = noGet;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception e) {

            }

            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            get.incrementAndGet();
                        }
                    } else {
                        noGet.incrementAndGet();
                    }
                } catch (Exception e) {

                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
