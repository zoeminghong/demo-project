package com.zeros.lambda;

/**
 * Created by 迹_Jason on 2017/9/28.
 */
public class Api implements Comparable<Api>{

    public Api(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Api o) {
        return name.compareTo(o.getName());
    }
}
