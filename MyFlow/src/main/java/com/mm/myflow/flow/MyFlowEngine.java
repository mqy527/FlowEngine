package com.mm.myflow.flow;

import java.util.HashMap;
import java.util.Map;

import com.mm.myflow.ex.FlowErrorException;
import com.mm.myflow.ex.FlowNotFoundException;
import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.FlowNode;
import com.mm.myflow.node.GateWayFlowNode;
import com.mm.myflow.node.SuspendedFlowNode;
import com.mm.myflow.node.impl.DefaultEndFlowNode;
import com.mm.myflow.node.recorder.FlowNodeRecorder;

/**
 * @author mengqingyan 2018/12/3
 */
public class MyFlowEngine implements FlowEngine, FlowRegistrar {

    private FlowNodeRecorder flowNodeRecorder;

    private Map<String, Flow> flowMap = new HashMap<>();

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
            tmpFlowNode = flow.getFlowNode(businessCurrentPosition.getFlowNodeName());
        }

        do {
            Object executeResult = tmpFlowNode.execute(executionContext);
            flowNodeRecorder.record(flow, tmpFlowNode, executionContext, executeResult);
            executionContext.setPreResult(executeResult);
            if (tmpFlowNode instanceof GateWayFlowNode) {
                tmpFlowNode = flow.getFlowNode(executeResult.toString());
            } else {
                tmpFlowNode = flow.getNextFlowNode(tmpFlowNode.getName(), executionContext);
            }
        } while (tmpFlowNode != null && !(tmpFlowNode instanceof SuspendedFlowNode));
        // 执行最后一个暂停的节点
        if((tmpFlowNode instanceof DefaultEndFlowNode)) {
            Object executeResult = tmpFlowNode.execute(executionContext);
            flowNodeRecorder.record(flow, tmpFlowNode, executionContext, executeResult);
        }
        flowNodeRecorder.record(flow, tmpFlowNode, executionContext, "STOP_BY");

    }

    @Override
    public void registerFLow(Flow flow) {
        flowMap.put(flow.getFlowName(), flow);
    }

    public void setFlowNodeRecorder(FlowNodeRecorder flowNodeRecorder) {
        this.flowNodeRecorder = flowNodeRecorder;
    }
}
