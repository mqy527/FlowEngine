package com.mm.myflow.node.impl;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.AbstractFlowNode;
import com.mm.myflow.node.EndFlowNode;
import com.mm.myflow.node.SuspendedFlowNode;

/**
 * 流程结束节点
 * @author mengqingyan 2018/12/3
 */
public class DefaultEndFlowNode extends AbstractFlowNode implements EndFlowNode {

    public DefaultEndFlowNode(String name, String description) {
        super(name, description);
    }

    @Override
    public Object execute(ExecutionContext executionContext) {
        return null;
    }
}
