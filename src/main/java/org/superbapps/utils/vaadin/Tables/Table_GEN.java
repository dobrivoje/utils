/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.Tables;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import java.util.List;

/**
 *
 * @author д06ри
 */
public class Table_GEN<T> extends Table implements IRefreshVisualContainer {

    // protected final BeanItemContainer<T> beanContainer;
    protected List list;

    public Table_GEN() {
        init();
    }

    public Table_GEN(BeanItemContainer<T> beanContainer /*, List list*/) {
        // this.beanContainer = beanContainer;
        // this.list = list;
        initWithBeanContainer(beanContainer);
    }

    protected void initWithBeanContainer(BeanItemContainer<T> beanContainer1) {
        setContainerDataSource(beanContainer1);
        init();
    }

    private void init() {
        // updateBeanItemContainer(list);
        super.setSizeFull();
        super.setPageLength(20);
        super.setCacheRate(4);
        super.setSelectable(true);
        super.setColumnCollapsingAllowed(true);
        super.setImmediate(true);
    }

    protected final void updateBeanItemContainer(List list) {
        // this.beanContainer.removeAllItems();
        // this.beanContainer.addAll(list);
    }

    @Override
    public void refreshVisualContainer() {
        // updateBeanItemContainer(this.list);
        markAsDirtyRecursive();
    }
}
