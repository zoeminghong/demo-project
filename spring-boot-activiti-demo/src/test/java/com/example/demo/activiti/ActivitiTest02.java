package com.example.demo.activiti;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

/**
 * Created by 迹_Jason on 2017/9/1.
 */
public class ActivitiTest02 implements Command<Void> {
    @Override
    public Void execute(CommandContext commandContext) {
        return null;
    }
}
