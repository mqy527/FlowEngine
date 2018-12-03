package com.mm.myflow.flow;

import com.mm.myflow.ex.FlowConfigErrorException;
import com.mm.myflow.ex.FlowErrorException;
import com.mm.myflow.ex.FlowNotFoundException;
import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengqingyan 2018/12/3
 */
public class MyFlowEngine implements FlowEngine {

    private FlowNodeRecorder  flowNodeRecorder;

    private Map<String, Flow> flowMap = new HashMap<>();

    public void init() {

    }

    @Override
    public void triggerFlow(String flowName, ExecutionContext executionContext) throws Exception {
        Flow flow;
        FlowNode tmpFlowNode;
        BusinessCurrentPosition businessCurrentPosition = flowNodeRecorder.getBusinessCurrentFlowNodePosition(flowName,
                executionContext);

        if (businessCurrentPosition == null) {
            flow = flowMap.get(flowName);
            if (flow == null) {
                throw new FlowNotFoundException("找不到流程： " + flowName);
            }
            tmpFlowNode = flow.getFirstFlowNode();
        } else {
            if (flowName != null && !flowName.isEmpty()) {
                if (!flowName.equals(businessCurrentPosition.getFlowName())) {
                    throw new FlowErrorException("传入的流程名与下一个流程名不一致");
                }
            }
            flow = flowMap.get(businessCurrentPosition.getFlowName());
            if (flow == null) {
                throw new FlowNotFoundException("找不到流程： " + flowName);
            }
            tmpFlowNode = flow.getNextFlowNode(businessCurrentPosition.getFlowNodeName(), executionContext);
        }

        while (!(tmpFlowNode instanceof SuspendedFlowNode)) {
            Object executeResult = tmpFlowNode.execute(executionContext);
            flowNodeRecorder.record(tmpFlowNode, executionContext, executeResult);
            if (tmpFlowNode instanceof GateWayFlowNode) {
                if (GateWayFlowNode.PAUSE.equals(executeResult)) {
                    break;
                }
                tmpFlowNode = flow.getNextFlowNode(executeResult.toString(), executionContext);
            }

            tmpFlowNode = flow.getNextFlowNode(tmpFlowNode.getName(), executionContext);
        }
    }

}
