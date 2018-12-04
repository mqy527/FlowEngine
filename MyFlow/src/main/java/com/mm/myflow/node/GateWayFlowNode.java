package com.mm.myflow.node;

import com.mm.myflow.exe.ExecutionContext;

/**
 * 分支节点
 * @author mengqingyan 2018/12/3
 */
public interface GateWayFlowNode extends FlowNode {

    /**
     * 获取下一节点名称
     * @param executionContext
     * @return
     */
    String execute(ExecutionContext executionContext);
}
