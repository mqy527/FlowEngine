package com.mm.myflow.node.impl;

import com.mm.myflow.exe.ExecutionContext;
import com.mm.myflow.node.AbstractFlowNode;
import com.mm.myflow.node.UserTaskFlowNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mengqingyan 2018/12/4
 */
public class DefaultUserTaskFlowNode extends AbstractFlowNode implements UserTaskFlowNode {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final String   role;

    public DefaultUserTaskFlowNode(String role) {
        this.role = role;
    }

    public DefaultUserTaskFlowNode(String name, String description, String role) {
        super(name, description);
        this.role = role;
    }

    public String getUserRole() {
        return this.role;
    }

    @Override
    public Object execute(ExecutionContext executionContext) {
        logger.debug("arrive in user task:{}, role:{}", this.getName(), this.role);
        return null;
    }
}
