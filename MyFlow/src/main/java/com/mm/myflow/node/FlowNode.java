package com.mm.myflow.node;

import com.mm.myflow.exe.ExecutionContext;

/**
 * @author mengqingyan 2018/12/3
 */
public interface FlowNode<R> {

    String getName();

    String getDescription();

    /**
     * 当流程到该节点时，触发该方法
     * @param executionContext
     * @return
     */
    R execute(ExecutionContext executionContext);
}
