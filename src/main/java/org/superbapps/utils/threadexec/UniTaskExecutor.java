package org.superbapps.utils.threadexec;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.superbapps.utils.common.ObjectHolders.ObjectHolder;

/**
 *
 * @author д06ри, dobri7@gmail.com
 *
 * Klasa za izvršavanje callable<T>thread-ova.<br>
 * Zbog pojednostavljenja, odgovor tipa T, je String
 */
public class UniTaskExecutor {

    //<editor-fold defaultstate="collapsed" desc="infra">
    private final Callable<String> callable;
    private ExecutorService ES;

    // zbog potrebe pristupa unutar interfejsa, moramo koristiti holder-e
    private final ObjectHolder<String> msgTitle;
    private final ObjectHolder<String> msgContent;

    public UniTaskExecutor(Callable<String> callable) {
        this.callable = callable;

        msgTitle = new ObjectHolder<>("");
        msgContent = new ObjectHolder<>("");
    }
    //</editor-fold>

    /**
     * Metod koji izvršava "callable thread"
     *
     * @param successfullMessage Poruka posle uspešnog izvršenja
     * @param server Ime servera za poruku o grešci
     * @param task Ime taks-a za poruku o grešci
     */
    public void executeRemoteShellCommand(String successfullMessage, String server, String task) {
        if (ES == null || ES.isTerminated()) {
            ES = Executors.newSingleThreadExecutor();
        }

        final Future<String> execTask = ES.submit(this.callable);

        ES.execute(new Runnable() {
            @Override
            public void run() {
                String errorMsg = "Server [" + server + "], task [" + task + "]\n";

                try {
                    String execTaskReturnMessage = execTask.get();

                    msgTitle.set("Obaveštenje");
                    msgContent.set(execTaskReturnMessage.isEmpty() ? successfullMessage : successfullMessage + "\n" + execTaskReturnMessage);

                    Logger.getLogger("#### UniTaskExecutor ---> ").log(Level.INFO, msgContent.get());

                } catch (Exception e) {
                    msgTitle.set("Greška");
                    msgContent.set(errorMsg + e.getMessage());

                    Logger.getLogger("#### UniTaskExecutor ---> ").log(Level.INFO, "Greška : {0}", errorMsg + e.getMessage());
                } finally {
                    Logger.getLogger("#### UniTaskExecutor ---> ").log(Level.INFO, msgContent.get());

                    Notification n = new Notification(msgTitle.get(), msgContent.get(), Notification.Type.ERROR_MESSAGE);
                    n.setDelayMsec(5000);

                    n.show(UI.getCurrent().getPage());
                }
            }
        });

        ES.shutdown();
    }

}
