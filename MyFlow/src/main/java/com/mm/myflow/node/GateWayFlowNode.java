package com.mm.myflow.node;

import com.mm.myflow.exe.ExecutionContext;

/**
 * @author mengqingyan 2018/12/3
 */
public interface GateWayFlowNode extends FlowNode<String> {

    /**
     * 当获取的下一节点的名称为PAUSE时,流程继续停留在该节点
     */
    String PAUSE = "_PAUSE";

    /**
     * 获取下一节点名称
     * @param executionContext
     * @return
     */
    String execute(ExecutionContext executionContext);
}
