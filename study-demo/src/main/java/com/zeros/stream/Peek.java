package com.zeros.stream;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by 迹_Jason on 2017/6/26.
 * study-demo
 */
public class Peek {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");
        Set<Integer> set = new HashSet<>();
        list.stream().peek(e -> System.out.println("Filtered value: " + e));
    }
}
