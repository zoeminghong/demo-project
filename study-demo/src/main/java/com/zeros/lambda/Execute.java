package com.zeros.lambda;

/**
 * Created by 迹_Jason on 2017/6/26.
 */
public class Execute {
    /**
     * 1、定义一个函数式接口（是不是泛型都可以）
     * 2、定义一个泛型的类，其中方法的参数中包含1中定义的接口
     * 3、在方法中调用接口的方法
     * 4、调用
     * @param args
     */
    public static void main(String[] args) {
        Active<String> ls = new Active<>();
        ls.add("first");
        ls.add("second");
        ls.get(v -> {
            System.out.println(v);
        });
    }
}
