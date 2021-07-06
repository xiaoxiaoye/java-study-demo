package org.example.study.juc;

public class FinalExample {
    int i;
    final int j;
    static FinalExample obj;

    public FinalExample() {
        this.i = 1;
        this.j = 2;
    }

    // 线程A
    public static void writer() {
        obj = new FinalExample();
    }

    // 线程B
    public static void  reader() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
    }
}
