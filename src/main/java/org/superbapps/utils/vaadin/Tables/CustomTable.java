/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.Tables;

import com.vaadin.v7.ui.Table;

/**
 *
 * @author д06ри
 */
public class CustomTable extends Table implements IRefreshVisualContainer {

    public CustomTable() {
        super();
    }

    @Override
    public void refreshVisualContainer() {
        markAsDirtyRecursive();
    }
}
