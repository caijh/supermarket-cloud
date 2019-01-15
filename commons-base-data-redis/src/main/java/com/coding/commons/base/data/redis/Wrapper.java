package com.coding.commons.base.data.redis;

public class Wrapper<T> {

    private T data;

    public Wrapper() {
    }

    public Wrapper(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
