package com.example.mybatis.controller;

import com.example.mybatis.model.TestModel;
import com.example.mybatis.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;

    public PageInfo<TestModel> test(@RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(1, 20);
        List<TestModel> list =testService.findAll();
        PageInfo<TestModel> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getEndRow()+pageInfo.getPageNum()+pageInfo.getPageSize());
        return pageInfo;
    }
}
