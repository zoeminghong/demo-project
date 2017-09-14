package com.example.mybatis.service;

import com.example.mybatis.model.TestModel;
import com.example.mybatis.util.Page;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
public interface TestService {
    Page<TestModel> findAll(Page.PageRequest pageRequest);
}
