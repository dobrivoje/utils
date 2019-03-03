package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.ui.Alignment;
import com.vaadin.v7.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.HorizontalLayout;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class FancyStatus extends HorizontalLayout {

    private CheckBox checkBox;

    /**
     * @param active Ako je true, checkbox je štikliran
     * @param leftComponent Leva komponenta
     */
    public FancyStatus(boolean active, Component leftComponent) {
        this(active, false, leftComponent);
    }

    /**
     * @param active Ako je true, checkbox je štikliran
     * @param showCheckBox Ako je true, checkbox se pojavljuje s desne strane
     * @param leftComponent Leva komponenta
     */
    public FancyStatus(boolean active, boolean showCheckBox, Component leftComponent) {
        setup(active, showCheckBox, leftComponent);
    }

    private void setup(boolean active, boolean showCheckBox, Component component) {
        setMargin(true);
        setSpacing(true);
        
        addComponent(component);

        if (showCheckBox) {
            checkBox = new CheckBox();
            checkBox.setEnabled(false);
            checkBox.setValue(active);

            addComponent(checkBox);
            setComponentAlignment(checkBox, Alignment.MIDDLE_CENTER);
        }

        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
    }
}
