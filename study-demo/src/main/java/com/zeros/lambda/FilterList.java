package com.zeros.lambda;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by 迹_Jason on 2017/9/30.
 */
public class FilterList {

    public static void main(String[] args) {
        List<Api> list= new ArrayList<>();
        list.add(new Api("http://www.trc.com"));
        list.add(new Api("http://www.trc.com/com/"));
        list.add(new Api("http://www.trc.com/com"));

        list.parallelStream().filter(api->api.getName().equals("http://www.trc.com/")).map(Api::getName).collect(toList());
    }
}
