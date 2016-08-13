package org.superbapps.utils.common.ObjectHolders;

public class ObjectHolder<T> {

    private T obj;

    public ObjectHolder(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

}
