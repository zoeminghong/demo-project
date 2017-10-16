package com.example.mybatis.service;

import com.example.mybatis.dao.TestDao;
import com.example.mybatis.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;

    @Override
    public List<TestModel> findAll() {
        return testDao.selectNewestValidateCode();
    }
}
