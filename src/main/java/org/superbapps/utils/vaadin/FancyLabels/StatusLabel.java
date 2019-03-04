package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import java.util.HashMap;
import java.util.Map;
import org.superbapps.utils.common.Enums.Statuses;

/**
 *
 * @author д06ри
 */
public class StatusLabel extends Label {

    private final Map<Statuses, Statuses> color = new HashMap<>();
    private String iconCode;

    public StatusLabel() {
        super.setContentMode(ContentMode.HTML);
        super.setSizeUndefined();

        color.put(Statuses.OK, Statuses.OK_COLOR);
        color.put(Statuses.BLACK_LIST, Statuses.BLACK_LIST_COLOR);
        color.put(Statuses.IN_PROGRESS, Statuses.IN_PROGRESS_COLOR);
        color.put(Statuses.UNKNOWN, Statuses.UNKNOWN_COLOR);
        color.put(Statuses.NO_LICENCE, Statuses.NO_LICENCE_COLOR);
    }

    public StatusLabel(Statuses status, String property) {
        this();
        render(status);
        super.setValue(iconCode + " " + property);
    }

    public StatusLabel(String rgbColor, String property) {
        this();
        render(rgbColor);
        super.setValue(iconCode + " " + property);
    }

    private void render(Statuses status) {
        render(color.get(status).toString());
    }

    private void render(String color) {
        iconCode = "<span class=\"v-icon\" style=\"font-family: "
                + VaadinIcons.CIRCLE.getFontFamily()
                + ";color:"
                + color
                + "\">&#x"
                + Integer
                        .toHexString(VaadinIcons.CIRCLE.getCodepoint())
                + ";</span>";
    }
}
