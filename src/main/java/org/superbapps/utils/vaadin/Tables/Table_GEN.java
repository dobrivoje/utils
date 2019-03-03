package org.superbapps.utils.vaadin.Tables;

import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Table;
import java.util.List;

/**
 *
 * @author д06ри
 */
public class Table_GEN<T> extends Table {

    // protected final BeanItemContainer<T> beanContainer;
    // protected List list;
    public Table_GEN() {
        init();
    }

    public Table_GEN(BeanItemContainer<T> beanContainer /*, List list*/) {
        // this.beanContainer = beanContainer;
        // this.list = list;
        initWithBeanContainer(beanContainer);
    }

    protected final void initWithBeanContainer(BeanItemContainer<T> beanContainer1) {
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

    protected void updateBeanItemContainer(List<T> newList, Class<T> type) {
        // this.beanContainer.removeAllItems();
        // this.beanContainer.addAll(list);
        setContainerDataSource(new BeanItemContainer(type, newList));
        setVisibleColumns();
    }

    protected void setVisibleColumns() {
    }
}
