package org.superbapps.utils.vaadin.utils;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import org.superbapps.utils.common.ObjectHolders.ObjectCounter;
import org.superbapps.utils.common.exceptions.NoPriceException;
import org.superbapps.utils.common.hashing.IHash;

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

    public static void checkExceptionError(String naslov, Exception ex) {
        String m = ex.getMessage().toLowerCase(), s = "";

        if (m.contains("violates unique constraint")) {
            s = "Ovaj podatak već postoji !";
        } else if (m.contains("violates not-null constraint")) {
            s = "Oba podatka moraju biti izabrana !";
        } else if (m.contains("commit failed")) {
            s = "Polja označena zvezdicom moraju biti popunjena !";
        } else if (ex instanceof NoPriceException) {
            s = "Proizvod nema cenu.";
        } else {
            s = ex.getMessage();
        }

        showCentralNotif(naslov, s, Notification.Type.ERROR_MESSAGE);
    }

    public static void checkExceptionError(Exception ex) {
        checkExceptionError("Greška", ex);
    }

    public static String createPassword(String p1, String p2, IHash hashBean,
            Label resultLabel, ObjectCounter oc) {

        String m1 = "Šifre se razlikuju.";
        String hashedPass = null;
        resultLabel.setContentMode(ContentMode.HTML);

        if (oc.get() % 2 == 0) {
            resultLabel.setEnabled(true);

            if (p1.equals(p2) && !p1.isEmpty() && !p2.isEmpty()) {
                resultLabel.setStyleName("textGreen");
                resultLabel.setValue("Šifra je ispravna.");
                resultLabel.setVisible(true);

                hashedPass = hashBean.hash(p2, IHash.$512);
            } else {
                resultLabel.setStyleName("textRed");
                resultLabel.setValue(m1);
                resultLabel.setVisible(true);

                // throw new Exception(m1);
            }
        } else {
            resultLabel.setStyleName("textRed");
            resultLabel.setValue(m1);
            resultLabel.setVisible(true);
        }

        return hashedPass;
    }

    public static boolean checkPasswords(String p1, String p2, Label messageLabel) {
        String m1 = "Šifre se razlikuju.";
        boolean f;

        messageLabel.setContentMode(ContentMode.HTML);

        if (!p1.isEmpty() && !p2.isEmpty() && p1.equals(p2)) {
            messageLabel.setStyleName("textGreen");
            messageLabel.setValue("Šifra je ispravna.");
            messageLabel.setVisible(true);

            f = true;
        } else if (p1.isEmpty() && p2.isEmpty()) {
            messageLabel.setEnabled(false);
            messageLabel.setVisible(false);
            messageLabel.removeStyleName("textRed");
            messageLabel.removeStyleName("textGreen");

            f = false;
        } else {
            messageLabel.setStyleName("textRed");
            messageLabel.setValue(m1);
            messageLabel.setVisible(true);

            f = false;
        }

        return f;
    }

    public static boolean isSmallDevice() {
        return isSmallDevice(800);
    }

    public static boolean isSmallDevice(int pageWidth) {
        return Page.getCurrent().getBrowserWindowWidth() < pageWidth;
    }
}
