package com.example.mybatis.controller;

import com.example.mybatis.model.TestModel;
import com.example.mybatis.service.TestService;
import com.example.mybatis.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;

    public Page<TestModel> test(@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page.PageRequest pageRequest = new Page.PageRequest(pageNum, pageSize);
        return testService.findAll(pageRequest);
    }
}
