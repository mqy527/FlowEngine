package com.mm.myflow.mock.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.impl.AbstractServiceFlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public class MockServiceFlowNode extends AbstractServiceFlowNode {


    public MockServiceFlowNode(String name, String description) {
        super(name, description);
    }

    @Override
    public Object execute(ExecutionContext executionContext) {
        System.out.println(getName() + " for businessKey:" + executionContext.getBusinessKey());
        return executionContext.getPreResult();
    }
}
