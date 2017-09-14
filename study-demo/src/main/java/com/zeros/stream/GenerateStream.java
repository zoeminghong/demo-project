package com.zeros.stream;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by 迹_Jason on 2017/6/26.
 */
public class GenerateStream {
    /**
     * 1、achieve Supplier implement
     * 2、execute Stream.generate by Supplier Implement
     * @param args
     */
    public static void main(String[] args) {
        Random seed = new Random();
        //根据什么规则生成数据源
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);
        //Another way
        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
                limit(10).forEach(System.out::println);
    }
}
