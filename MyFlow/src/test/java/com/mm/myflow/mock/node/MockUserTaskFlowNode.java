package com.mm.myflow.mock.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.impl.DefaultUserTaskFlowNode;

/**
 * @author mengqingyan 2018/12/4
 */
public class MockUserTaskFlowNode extends DefaultUserTaskFlowNode {


    private boolean success;

    public MockUserTaskFlowNode(String name, String description, String role) {
        super(name, description, role);
    }

    @Override
    public Object execute(ExecutionContext executionContext) {
        Object execute = super.execute(executionContext);
        if(success) {
            System.out.println(getName() + " success for business:" + executionContext.getBusinessKey());
        } else {
            System.out.println(getName() + " fail for business:" + executionContext.getBusinessKey());
        }
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
