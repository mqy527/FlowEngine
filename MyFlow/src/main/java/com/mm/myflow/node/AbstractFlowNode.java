package com.mm.myflow.node;


import java.util.Objects;

/**
 * @author mengqingyan 2018/12/3
 */
public abstract class AbstractFlowNode<R> implements FlowNode<R> {

    private String name;
    private String description;

    public AbstractFlowNode() {
    }

    public AbstractFlowNode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFlowNode that = (AbstractFlowNode) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description);
    }

}
