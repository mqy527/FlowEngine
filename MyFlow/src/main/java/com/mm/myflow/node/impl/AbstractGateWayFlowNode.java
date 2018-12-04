package com.mm.myflow.node.impl;

import com.mm.myflow.node.AbstractFlowNode;
import com.mm.myflow.node.GateWayFlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public abstract class AbstractGateWayFlowNode extends AbstractFlowNode implements GateWayFlowNode {

    public AbstractGateWayFlowNode(String name, String description) {
        super(name, description);
    }
}
