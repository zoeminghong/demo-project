package com.demo;

/**
 * Created by 迹_Jason on 2017/9/28.
 */
import org.springframework.stereotype.Service;

import com.demo.ITestService;

@Service
public class TestServiceImpl implements ITestService {
    public String getName() {
        return "gege";
    }
}

