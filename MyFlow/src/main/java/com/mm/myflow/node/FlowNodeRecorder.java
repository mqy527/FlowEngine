package com.mm.myflow.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.flow.BusinessCurrentPosition;

/**
 * @author mengqingyan 2018/12/3
 */
public interface FlowNodeRecorder {

    void record(FlowNode tmpFlowNode, ExecutionContext executionContext, Object executeResult);

    BusinessCurrentPosition getBusinessCurrentFlowNodePosition(String flowName, ExecutionContext executionContext);
}
