package org.example.study.juc;

public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance;

    private int i;

    private DoubleCheckSingleton() {
        i = 1;
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    // 此处分为3步： 1-分配对象的内存空间， 2-初始化对象，3-设置instance指向刚才分配的地址
                    // 如果volatile没有设置， 2和3可能会发生重排序， 多线程环境下，另外一个线程可能会拿到
                    // 未初始化完成的instance
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
