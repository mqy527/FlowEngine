package com.mm.myflow.flow;

import com.mm.myflow.node.FlowNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mengqingyan 2018/12/3
 */
public class RelationNode {
    private final FlowNode       currentFlowNode;

    private final Set<FlowNode> preFlowNodes = new HashSet<>();

    private final Set<FlowNode> nextFlowNodes = new HashSet<>();


    public RelationNode(FlowNode currentFlowNode, List<FlowNode> preFlowNodes, List<FlowNode> nextFlowNodes) {
        this.currentFlowNode = currentFlowNode;
        if(preFlowNodes != null && !preFlowNodes.isEmpty()) {
            for (FlowNode preFlowNode : preFlowNodes) {
                this.preFlowNodes.add(preFlowNode);
            }
        }
        if(nextFlowNodes != null && !nextFlowNodes.isEmpty()) {
            for (FlowNode nextFlowNode : nextFlowNodes) {
                this.nextFlowNodes.add(nextFlowNode);
            }
        }
    }

    public FlowNode getCurrentFlowNode() {
        return currentFlowNode;
    }


    public Set<FlowNode> getPreFlowNodes() {
        return preFlowNodes;
    }


    public Set<FlowNode> getNextFlowNodes() {
        return nextFlowNodes;
    }

}
