package org.example.study.juc;

public class InnerClassSingleton {
    private int i;
    private InnerClassSingleton() {
        i = 1;
    }

    public int getI() {
        return i;
    }

    private static class InstanceHolder {
        public static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return InstanceHolder.instance;
    }
}
