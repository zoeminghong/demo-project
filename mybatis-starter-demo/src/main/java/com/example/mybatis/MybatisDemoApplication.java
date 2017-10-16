package com.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.example.mybatis"
        }
)
@MapperScan(basePackages = "com.example.mybatis.dao")
public class MybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }
}
