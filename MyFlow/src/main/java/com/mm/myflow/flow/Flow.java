package com.mm.myflow.flow;

import com.mm.myflow.ex.FlowConfigErrorException;
import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.FlowNode;

import java.util.List;
import java.util.Set;

/**
 * @author mengqingyan 2018/12/3
 */
public interface Flow {

    FlowNode getFirstFlowNode();

    void addFlowNode(FlowNode currentFlowNode, List<FlowNode> preFlowNodes, List<FlowNode> nextFlowNodes);

    String getFlowName();

    FlowNode getFlowNode(String flowNodeName);

    Set<FlowNode> getNextFlowNodes(String flowNodeName);

    Set<FlowNode> getPreFlowNodes(String flowNodeName);

    FlowNode getNextFlowNode(String flowNodeName, ExecutionContext executionContext) throws FlowConfigErrorException;
}
