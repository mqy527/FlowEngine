package com.mm.myflow.mock.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.impl.DefaultEndFlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public class MockEndFlowNode extends DefaultEndFlowNode {
    public MockEndFlowNode(String name, String description) {
        super(name, description);
    }
    @Override
    public Object execute(ExecutionContext executionContext) {
        System.out.println("END,businessKey:" + executionContext.getBusinessKey() + ", preResult: " + executionContext.getPreResult());
        return super.execute(executionContext);
    }
}
