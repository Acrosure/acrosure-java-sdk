package com.acrosure.form;

public class DataGetForm<T> {
    private String handler;
    private T[] dependencies;

    public DataGetForm() {
        this.handler = "";
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public T[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(T[] dependencies) {
        this.dependencies = dependencies;
    }
}
