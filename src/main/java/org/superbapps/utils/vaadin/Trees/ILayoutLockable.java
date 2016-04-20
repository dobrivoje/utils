/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.Trees;

import com.vaadin.ui.Layout;

/**
 * Interface for locking layout fields. <br>
 * Can be used in situations when necessary to lock form fields, eg. when
 * implementing access rights to form, for example.
 *
 * @author root
 */
public interface ILayoutLockable extends Layout {

    /**
     *
     * @param lockFields When true, all fields on the layout are locked.
     */
    void setLayoutFieldsLocked(boolean lockFields);
}
