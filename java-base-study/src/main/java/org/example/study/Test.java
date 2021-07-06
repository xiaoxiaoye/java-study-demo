package org.example.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>(Arrays.asList(1,2,3,4,5));
        for (Integer i : list) {
            System.out.println(i);
        }

        List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
