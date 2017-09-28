package com.zeros.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 迹_Jason on 2017/9/28.
 */
public class SortList {

    public static void main(String[] args) {
        List<Api> list = new ArrayList<Api>();
        list.add(new Api("http://www.trc.com"));
        list.add(new Api("http://www.trc.com/com/"));
        list.add(new Api("http://www.trc.com/com"));

        List<Api> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Name: "+e.getName()));
    }
}
