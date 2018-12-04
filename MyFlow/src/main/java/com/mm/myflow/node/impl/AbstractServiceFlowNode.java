package com.mm.myflow.node.impl;

import com.mm.myflow.node.AbstractFlowNode;
import com.mm.myflow.node.ServiceFlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public abstract class AbstractServiceFlowNode<R> extends AbstractFlowNode<R> implements ServiceFlowNode<R> {
    public AbstractServiceFlowNode(String name, String description) {
        super(name, description);
    }
}
