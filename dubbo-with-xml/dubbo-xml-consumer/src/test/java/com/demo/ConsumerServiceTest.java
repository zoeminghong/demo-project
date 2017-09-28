package com.demo;

/**
 * Created by 迹_Jason on 2017/9/28.
 */
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerServiceTest {
    Logger logger=Logger.getLogger(ConsumerServiceTest.class);
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
        context.start();
        ITestService testService = (ITestService) context.getBean("testService");
        System.out.println(testService.getName());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}