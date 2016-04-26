/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import java.util.HashMap;
import java.util.Map;
import org.superbapps.utils.common.Enums.WorkingPlansStatuses;

/**
 *
 * @author root
 */
public class WPSLabel extends Label {

    private final Map<WorkingPlansStatuses, WorkingPlansStatuses> color = new HashMap<>();
    private String iconCode;

    public WPSLabel() {
        setContentMode(ContentMode.HTML);
        setSizeUndefined();

        color.put(WorkingPlansStatuses.FINISHED, WorkingPlansStatuses.FINISHED_COLOR);
        color.put(WorkingPlansStatuses.IN_PROGRESS, WorkingPlansStatuses.IN_PROGRESS_COLOR);
        color.put(WorkingPlansStatuses.UNKNOWN, WorkingPlansStatuses.UNKNOWN_COLOR);
    }

    public WPSLabel(WorkingPlansStatuses status, String property) {
        this();
        render(status);
        setValue(iconCode + " " + property);
    }

    private void render(WorkingPlansStatuses status) {
        iconCode = "<span class=\"v-icon\" style=\"font-family: "
                + FontAwesome.CIRCLE.getFontFamily()
                + ";color:"
                + color.get(status).toString()
                + "\">&#x"
                + Integer
                .toHexString(FontAwesome.CIRCLE.getCodepoint())
                + ";</span>";
    }
}
