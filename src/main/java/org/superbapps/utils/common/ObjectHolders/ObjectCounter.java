package org.superbapps.utils.common.ObjectHolders;

public class ObjectCounter {

    //<editor-fold defaultstate="collapsed" desc="infra">
    private int counter;

    public ObjectCounter() {
        this.counter = 0;
    }

    public ObjectCounter(int counter) {
        this.counter = counter;
    }

    public int get() {
        return counter;
    }

    public void set(int counter) {
        this.counter = counter;
    }
    //</editor-fold>

    /**
     * Increment without returning a result.
     */
    public void inc1() {
        counter++;
    }

    /**
     * Increment with a result.
     *
     * @return
     */
    public int inc2() {
        return ++counter;
    }

    /**
     * Decrement without returning a result.
     */
    public void dec1() {
        counter--;
    }

    /**
     * Decrement with a result.
     *
     * @return
     */
    public int dec2() {
        return --counter;
    }

}
