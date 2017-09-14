package com.zeros.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 迹_Jason on 2017/6/26.
 */
public class Active<E> {
    private List<E> ls = new ArrayList<>();

    void add(E t) {
        ls.add(t);
    }

    Active get(FunctionD<E> functionD) {
        for (E l : ls) {
            functionD.get(l);
        }
        return this;
    }
}
