package org.superbapps.utils.vaadin.utils;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class VaadinUtils {

    public static void showCentralNotif(String caption, String description, Notification.Type notifType) {
        Notification n = new Notification(caption, description, notifType);
        n.setDelayMsec(3000);
        n.setHtmlContentAllowed(true);
        n.show(Page.getCurrent());
    }

    public static void showBottomRightNotif(String message) {
        Notification success = new Notification(message);
        success.setDelayMsec(2000);
        success.setStyleName("bar success small");
        success.setPosition(Position.BOTTOM_CENTER);
        success.show(Page.getCurrent());
    }

    public static void checkExceptionError(Exception ex) {
        String m = ex.getMessage().toLowerCase(), s = "";

        if (m.contains("violates unique constraint")) {
            s = "Ovaj zapis već postoji !";
        } else if (m.contains("violates not-null constraint")) {
            s = "Oba podatka moraju biti izabrana !";
        } else if (m.contains("commit failed")) {
            s = "Polja označena zvezdicom moraju biti popunjena !";
        }

        showCentralNotif("Greška", s, Notification.Type.ERROR_MESSAGE);
    }

}
