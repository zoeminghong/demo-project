package com.example;

import io.vertx.core.Vertx;

/**
 * Created on 2017/12/6.
 *
 * @author 迹_Jason
 */
public class HelloWorld {

    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(request->{
            request.response().end("hello world");
        }).listen(8088);
        System.out.println("service started");
    }
}
