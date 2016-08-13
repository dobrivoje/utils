/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.common.Enums;

/**
 *
 * @author д06ри
 */
public enum ViewModes {

    SIMPLE("Simple mode"),
    LICENCE("Licence mode!"),
    FULL("Full mode");

    private final String name;

    private ViewModes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
