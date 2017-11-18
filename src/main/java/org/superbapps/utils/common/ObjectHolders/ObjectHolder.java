package org.superbapps.utils.common.ObjectHolders;

public class ObjectHolder<T> {

    private T obj;

    public ObjectHolder() {
    }

    public ObjectHolder(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public void set(T obj) {
        this.obj = obj;
    }

    public String add(T obj) {
        if (obj instanceof String && this.obj instanceof String) {
            String to = (String) this.obj;
            to = to.concat((String) obj);

            return to;
        }

        return "";
    }

}
