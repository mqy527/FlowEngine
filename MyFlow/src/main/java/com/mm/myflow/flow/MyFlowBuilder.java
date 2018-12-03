package com.mm.myflow.flow;

import com.mm.myflow.node.FlowNode;

import java.util.List;

/**
 * @author mengqingyan 2018/12/3
 */
public class MyFlowBuilder {


    private final Flow flow;

    private MyFlowBuilder(Flow flow) {
        this.flow = flow;
    }

    public static MyFlowBuilder name(String flowName) {
        MyFlowBuilder myFlowBuilder = new MyFlowBuilder(new MyFlow(flowName));
        return myFlowBuilder;
    }

    public MyFlowBuilder node(FlowNode currentFlowNode, List<FlowNode> preFlowNodes, List<FlowNode> nextFlowNodes) {

        this.flow.addFlowNode(currentFlowNode, preFlowNodes, nextFlowNodes);
        return this;
    }

    public Flow flow() {
        return this.flow;
    }

}
