package com.mm.myflow.mock.engine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.flow.Flow;
import com.mm.myflow.flow.MyFlowBuilder;
import com.mm.myflow.flow.MyFlowEngine;
import com.mm.myflow.mock.node.MockEndFlowNode;
import com.mm.myflow.mock.node.MockGateWayFlowNode;
import com.mm.myflow.mock.node.MockServiceFlowNode;
import com.mm.myflow.mock.node.MockUserTaskFlowNode;
import com.mm.myflow.node.FlowNode;
import com.mm.myflow.node.recorder.FlowNodeRecorder;
import com.mm.myflow.node.recorder.LogFlowNodeRecorder;

/**
 * @author mengqingyan 2018/12/4
 */
public class TestFlow {

    private MockUserTaskFlowNode A_Audit_UserTask     = new MockUserTaskFlowNode("A_AUDIT", "A审核", "ROLE_A");

    private MockServiceFlowNode A_Audit_POST_Service = new MockServiceFlowNode("A_AUDIT_SERVICE", "A审核后逻辑");

    private MockGateWayFlowNode A_GATE               = new MockGateWayFlowNode("A_AUDIT_SERVICE_GATE", "A审核后逻辑GATE");

    private MockUserTaskFlowNode B_Audit_UserTask     = new MockUserTaskFlowNode("B_AUDIT", "B审核", "ROLE_B");

    private MockGateWayFlowNode  B_GATE               = new MockGateWayFlowNode("B_AUDIT_SERVICE_GATE", "B审核后逻辑GATE");

    private MockUserTaskFlowNode C_Audit_UserTask     = new MockUserTaskFlowNode("C_AUDIT", "C审核", "ROLE_C");

    private MockEndFlowNode END_NODE             = new MockEndFlowNode("END_NODE", "结束");

    @Test
    public void testEngine() throws Exception {

        Map<Object, String> resultGateMapA = new HashMap<>();
        resultGateMapA.put(true, "B_AUDIT");
        resultGateMapA.put(false, "END_NODE");
        A_GATE.setResultGateMap(resultGateMapA);

        Map<Object, String> resultGateMapB = new HashMap<>();
        resultGateMapB.put(true, "C_AUDIT");
        resultGateMapB.put(false, "END_NODE");
        B_GATE.setResultGateMap(resultGateMapB);

        FlowNodeRecorder flowNodeRecorder = new LogFlowNodeRecorder();
        MyFlowEngine myFlowEngine = new MyFlowEngine();
        myFlowEngine.setFlowNodeRecorder(flowNodeRecorder);

        String flowName = "TestFlow";
        Flow testFlow = MyFlowBuilder.name(flowName)
                .node(A_Audit_UserTask, null, toFlowNodes(A_Audit_POST_Service))
                .node(A_Audit_POST_Service, toFlowNodes(A_Audit_UserTask), toFlowNodes(A_GATE))
                .node(A_GATE, toFlowNodes(A_Audit_POST_Service), toFlowNodes(END_NODE, B_Audit_UserTask))
                .node(B_Audit_UserTask, toFlowNodes(A_GATE), toFlowNodes(B_GATE))
                .node(B_GATE, toFlowNodes(B_Audit_UserTask), toFlowNodes(END_NODE,C_Audit_UserTask))
                .node(C_Audit_UserTask, toFlowNodes(B_GATE), toFlowNodes(END_NODE))
                .node(END_NODE, toFlowNodes(A_GATE, B_GATE, C_Audit_UserTask), null)
                .flow();
        myFlowEngine.registerFLow(testFlow);

        ExecutionContext executionContext = new ExecutionContext();
        executionContext.setBusinessKey("BUSI_001");

        A_Audit_UserTask.setSuccess(true);
        myFlowEngine.triggerFlow(flowName, executionContext);

        B_Audit_UserTask.setSuccess(true);
        myFlowEngine.triggerFlow(flowName, executionContext);

        C_Audit_UserTask.setSuccess(true);
        myFlowEngine.triggerFlow(flowName, executionContext);

    }
    private List<FlowNode> toFlowNodes(FlowNode... flowNodes) {
        if(flowNodes == null) {
            return null;
        }
        return Arrays.asList(flowNodes);
    }
}
