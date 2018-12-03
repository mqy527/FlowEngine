package com.mm.myflow.flow;

import com.mm.myflow.ex.FlowConfigErrorException;
import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.FlowNode;
import com.mm.myflow.node.GateWayFlowNode;
import com.mm.myflow.node.ServiceFlowNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author mengqingyan 2018/12/3
 */
public class MyFlow implements Flow{

    private final String flowName;

    private FlowNode firstFlowNode;

    MyFlow(String flowName) {
        this.flowName = flowName;
    }

    /**
     * flowNodeName:RelationNode
     */
    private Map<String, RelationNode> relationNodeMap = new HashMap<>();

    @Override
    public void addFlowNode(FlowNode currentFlowNode, List<FlowNode> preFlowNodes, List<FlowNode> nextFlowNodes) {
        if(this.firstFlowNode == null) {
            this.firstFlowNode = currentFlowNode;
        }
        RelationNode relationNode = new RelationNode(currentFlowNode, preFlowNodes, nextFlowNodes);
        relationNodeMap.put(currentFlowNode.getName(), relationNode);
    }

    @Override
    public String getFlowName() {
        return flowName;
    }

    @Override
    public FlowNode getFlowNode(String flowNodeName) {
        return relationNodeMap.get(flowNodeName).getCurrentFlowNode();
    }

    @Override
    public Set<FlowNode> getNextFlowNodes(String flowNodeName) {
        return relationNodeMap.get(flowNodeName).getNextFlowNodes();
    }

    @Override
    public Set<FlowNode> getPreFlowNodes(String flowNodeName) {
        return relationNodeMap.get(flowNodeName).getPreFlowNodes();
    }

    @Override
    public FlowNode getNextFlowNode(String flowNodeName, ExecutionContext executionContext) throws FlowConfigErrorException {
        FlowNode currentFlowNode = getFlowNode(flowNodeName);
        Set<FlowNode> nextFlowNodes = getNextFlowNodes(flowNodeName);
        boolean isGateWayFlowNode = currentFlowNode instanceof GateWayFlowNode;
        if(!isGateWayFlowNode && nextFlowNodes.size() > 1) {
            throw new FlowConfigErrorException("非GateWayFlowNode 后面不能跟随多个节点");
        }

        if(!isGateWayFlowNode) {
            if(nextFlowNodes.isEmpty()) {
                return null;
            }
            return nextFlowNodes.iterator().next();
        }
        GateWayFlowNode gateWayFlowNode = (GateWayFlowNode) currentFlowNode;
        String nextFlowNodeName = gateWayFlowNode.execute(executionContext);
        if(GateWayFlowNode.PAUSE.equals(nextFlowNodeName)) {
            return currentFlowNode;
        }
        return getFlowNode(nextFlowNodeName);
    }

    public FlowNode getFirstFlowNode() {
        return firstFlowNode;
    }

    public void setFirstFlowNode(FlowNode firstFlowNode) {
        this.firstFlowNode = firstFlowNode;
    }
}
