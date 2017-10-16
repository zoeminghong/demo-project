package com.example.mybatis.service;

import com.example.mybatis.model.TestModel;

import java.util.List;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
public interface TestService {
    List<TestModel> findAll();
}
