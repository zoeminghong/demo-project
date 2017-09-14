package com.example.mybatis;

import com.example.mybatis.config.MybatisConfiguration;
import com.example.mybatis.controller.TestController;
import com.example.mybatis.model.TestModel;
import com.example.mybatis.util.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 迹_Jason on 2017/9/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisDemoApplication.class, MybatisConfiguration.class})
public class Test1 {

    @Autowired
    TestController testController;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("CREATE TABLE `test` (\n" +
                "  `id` varchar(64) NOT NULL ,\n" +
                "  `name` varchar(64) DEFAULT NULL,\n" +
                ")");
        jdbcTemplate.execute("insert INTO test (id,name)values('test1','name1')");
        jdbcTemplate.execute("insert INTO test (id,name)values('test2','name2')");
    }

    @Test
    public void test1() {
        Page<TestModel> testPage = testController.test(1, 1);
        Assert.assertTrue(testPage.getPagination().getPageTotal()==2);
        Assert.assertTrue(testPage.getData().get(0).getId().equalsIgnoreCase("test1"));
        testPage = testController.test(1, 2);
        Assert.assertTrue(testPage.getPagination().getPageTotal()==2);
        Assert.assertTrue(testPage.getData().get(0).getId().equalsIgnoreCase("test2"));
    }
}
