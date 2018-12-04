package com.mm.myflow.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.flow.BusinessCurrentPosition;
import com.mm.myflow.flow.Flow;

/**
 * @author mengqingyan 2018/12/3
 */
public interface FlowNodeRecorder {

    /**
     * 记录流程执行状态
     * @param flow
     * @param currentFlowNode
     * @param executionContext
     * @param executeResult
     */
    void record(Flow flow, FlowNode currentFlowNode, ExecutionContext executionContext, Object executeResult);

    /**
     * 获取某业务当前已执行到的节点，该节点并未触发execute方法
     * @param flowName
     * @param executionContext
     * @return
     */
    BusinessCurrentPosition getBusinessCurrentFlowNodePosition(String flowName, ExecutionContext executionContext);
}
