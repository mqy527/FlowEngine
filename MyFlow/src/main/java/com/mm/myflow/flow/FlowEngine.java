package com.mm.myflow.flow;

import com.mm.myflow.exe.ExecutionContext;

/**
 * @author mengqingyan 2018/12/3
 */
public interface FlowEngine {

    void triggerFlow(String flowName, ExecutionContext executionContext) throws Exception;
}
