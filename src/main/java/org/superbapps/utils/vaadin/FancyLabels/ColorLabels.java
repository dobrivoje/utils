package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import java.util.HashMap;
import java.util.Map;
import org.superbapps.utils.common.Enums.Statuses2;

/**
 *
 * @author д06ри
 */
public class ColorLabels extends Label {

    private final Map<Statuses2, Statuses2> color = new HashMap<>();
    private String iconCode;

    public ColorLabels() {
        setContentMode(ContentMode.HTML);
        setSizeUndefined();

        color.put(Statuses2.STARTED, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.IN_PROGRESS_COLOR, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.FAILED, Statuses2.APPLE_RED);
        color.put(Statuses2.EXECUTED, Statuses2.APPLE_GREEN);
        color.put(Statuses2.UNKNOWN, Statuses2.UNKNOWN_COLOR);
    }

    public ColorLabels(Statuses2 status, String property) {
        this();
        render(status);
        setValue(iconCode + " " + property);
    }

//    public ColorLabels(Statuses2 status) {
//        this();
//        render(status);
//        setValue(iconCode + " " + status.toString());
//    }

    private void render(Statuses2 status) {
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
