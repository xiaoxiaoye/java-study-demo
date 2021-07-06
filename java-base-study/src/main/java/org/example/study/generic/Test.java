package org.example.study.generic;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception{
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);

        list1.getClass().getMethod("add", Object.class).invoke(list1, "abc");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }
}
