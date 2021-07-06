package org.example.study.juc;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static void seconds(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {

        }
    }
}
