package com.mm.myflow.exe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengqingyan 2018/12/3
 */
public class ExecutionContext {
    /**
     * 当前业务key
     */
    private String businessKey;

    /**
     * 上一步执行结果
     */
    private Object preResult;

    private Map<String, Object> executionParams = new HashMap<String, Object>();

    public Object getPreResult() {
        return preResult;
    }

    public void setPreResult(Object preResult) {
        this.preResult = preResult;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, Object> getExecutionParams() {
        return executionParams;
    }

    public void setExecutionParams(Map<String, Object> executionParams) {
        this.executionParams = executionParams;
    }
}
