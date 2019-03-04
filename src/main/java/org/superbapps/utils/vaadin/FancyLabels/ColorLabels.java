package org.superbapps.utils.vaadin.FancyLabels;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
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
        super.setContentMode(ContentMode.HTML);
        super.setSizeUndefined();

        // oznake za statuse pokrenutih udaljenih komandi
        color.put(Statuses2.STARTED, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.IN_PROGRESS_COLOR, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.FAILED, Statuses2.APPLE_RED);
        color.put(Statuses2.EXECUTED, Statuses2.APPLE_GREEN);
        color.put(Statuses2.UNKNOWN, Statuses2.UNKNOWN_COLOR);

        // oznake boja
        color.put(Statuses2.APPLE_GREEN, Statuses2.APPLE_GREEN);
        color.put(Statuses2.APPLE_YELLOW, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.APPLE_RED, Statuses2.APPLE_RED);

        // oznake za šifre
        color.put(Statuses2.ACTIVE, Statuses2.APPLE_GREEN);
        color.put(Statuses2.PREVIOUS, Statuses2.APPLE_YELLOW);
        color.put(Statuses2.NOT_VALID, Statuses2.APPLE_RED);
    }

    public ColorLabels(Statuses2 status, String property) {
        this();
        render(status);
        setValue(iconCode + " " + property);
        setDescription(property);
    }

    public ColorLabels(Statuses2 status, String property, String description) {
        this(status, property);
        super.setDescription(description);
    }

    private void render(Statuses2 status) {
        render(color.get(status).toString());
    }

    private void render(String rgbColor) {
        iconCode = "<span class=\"v-icon\" style=\"font-family: "
                + VaadinIcons.CIRCLE.getFontFamily()
                + ";color:"
                + rgbColor
                + "\">&#x"
                + Integer
                        .toHexString(VaadinIcons.CIRCLE.getCodepoint())
                + ";</span>";
    }
}
