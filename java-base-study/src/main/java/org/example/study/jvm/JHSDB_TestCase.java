package org.example.study.jvm;

public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObject = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();
        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }
    private static class ObjectHolder{}

    public static void main(String[] args) {
//        Test test = new Test();
//        test.foo();
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        System.out.println("i4 = i5 " + (i4 == i5));

    }
}
