package com.example.mybatis.dao;

import com.example.mybatis.model.TestModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@Repository
public interface TestDao {

    List<TestModel> selectNewestValidateCode();
}
