package com.mm.myflow.node;


/**
 * @author mengqingyan 2018/12/3
 */
public interface UserTaskFlowNode<R> extends FlowNode<R>, SuspendedFlowNode {

    String getUserRole();

}
