package org.superbapps.utils.threadexec;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
    private final ObjectHolder<String> execMessage;
    private final ObjectHolder<String> msgTitle;
    private final ObjectHolder<String> msgContent;

    public UniTaskExecutor(Callable<String> callable) {
        this.callable = callable;

        execMessage = new ObjectHolder<>("");
        msgTitle = new ObjectHolder<>("");
        msgContent = new ObjectHolder<>("");
    }
    //</editor-fold>

    public void executeRemoteShellCommand(String successfullMessage) {
        if (ES == null || ES.isTerminated()) {
            ES = Executors.newSingleThreadExecutor();
        }

        final Future<String> execTask = ES.submit(this.callable);

        ES.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String execTaskReturnMessage = execTask.get();
                    execMessage.set(successfullMessage);
                    msgTitle.set("Obaveštenje");
                    msgContent.set(execTaskReturnMessage);

                    Logger.getLogger(UniTaskExecutor.class.getName()).log(Level.INFO, msgContent.get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(UniTaskExecutor.class.getName()).log(Level.INFO, "Gre\u0161ka : {0}", ex.getMessage());
                } finally {
                    Logger.getLogger(UniTaskExecutor.class.getName()).log(Level.INFO, msgContent.get());

                    Notification n = new Notification(msgTitle.get(), msgContent.get(), Notification.Type.ERROR_MESSAGE);
                    n.setDelayMsec(5000);

                    n.show(UI.getCurrent().getPage());
                }
            }
        });

        ES.shutdown();
    }

}
