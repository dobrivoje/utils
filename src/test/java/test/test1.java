package test;

import org.junit.Test;
import org.superbapps.utils.common.ObjectHolders.ObjectHolder;

public class test1 {

    private ObjectHolder<String> obj;
    private ObjectHolder<Integer> integercic;

    public test1() {
        obj = new ObjectHolder<>("testic");
        integercic = new ObjectHolder<>(12);
    }

    @Test
    public void hello() {
        System.err.println(obj.get());
        System.err.println(integercic.get()*2);
    }
}
