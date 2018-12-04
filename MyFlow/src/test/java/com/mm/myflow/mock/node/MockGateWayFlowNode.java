package com.mm.myflow.mock.node;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.impl.AbstractGateWayFlowNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengqingyan 2018/12/4
 */
public class MockGateWayFlowNode extends AbstractGateWayFlowNode {

    private Map<Object, String> resultGateMap;

    public MockGateWayFlowNode(String name, String description) {
        super(name, description);
    }

    @Override
    public String execute(ExecutionContext executionContext) {
        Object preResult = executionContext.getPreResult();
        String s = resultGateMap.get(preResult);
        System.out.println(getName() + " for businessKey:" + executionContext.getBusinessKey() + ", next path: " + s);

        return s;
    }

    public void setResultGateMap(Map<Object, String> resultGateMap) {
        this.resultGateMap = resultGateMap;
    }
}
