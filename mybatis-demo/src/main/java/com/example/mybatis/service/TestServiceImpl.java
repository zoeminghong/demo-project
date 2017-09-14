package com.example.mybatis.service;

import com.example.mybatis.dao.TestDao;
import com.example.mybatis.model.TestModel;
import com.example.mybatis.util.MybatisPageContext;
import com.example.mybatis.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;

    @Override
    public Page<TestModel> findAll(Page.PageRequest pageRequest) {
        MybatisPageContext.setPageRequest(pageRequest);
        testDao.selectNewestValidateCode();
        Page<TestModel> page = MybatisPageContext.getPage();
        MybatisPageContext.clearAll();
        return page;
    }
}
