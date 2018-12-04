package com.mm.myflow.node;


/**
 * 用户任务节点
 * @author mengqingyan 2018/12/3
 */
public interface UserTaskFlowNode extends FlowNode, SuspendedFlowNode {

    String getUserRole();

}
