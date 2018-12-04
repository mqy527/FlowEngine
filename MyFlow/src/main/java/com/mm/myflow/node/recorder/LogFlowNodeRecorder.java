package com.mm.myflow.node.recorder;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.flow.BusinessCurrentPosition;
import com.mm.myflow.flow.Flow;
import com.mm.myflow.node.FlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public class LogFlowNodeRecorder implements FlowNodeRecorder {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, BusinessCurrentPosition> businessCurrentPositionMap = new HashMap<>();


    @Override
    public void record(Flow flow, FlowNode currentFlowNode, ExecutionContext executionContext, Object executeResult) {
        logger.info("flowName:{}, currentFlowNodeName:{},businessKey:{},executeResult:{} ",
                new Object[]{flow.getFlowName(), currentFlowNode.getName(), executionContext.getBusinessKey(), executeResult});
        businessCurrentPositionMap.put(executionContext.getBusinessKey(), new BusinessCurrentPosition(flow.getFlowName(), currentFlowNode.getName()));
    }

    @Override
    public BusinessCurrentPosition getBusinessCurrentFlowNodePosition(String flowName, ExecutionContext executionContext) {
        return businessCurrentPositionMap.get(executionContext.getBusinessKey());
    }
}
