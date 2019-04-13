package org.superbapps.utils.threadexec;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
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
 * Klasa za izvršavanje callable thread-ova.<br>
 * Zbog pojednostavljenja, odgovor tipa T, je String
 */
public class UniTaskExecutor {

    private final Callable<String> callable;
    private final ExecutorService ES = Executors.newCachedThreadPool();

    public UniTaskExecutor(Callable<String> callable) {
        this.callable = callable;
    }

    /**
     * Metod koji izvršava "callable thread"
     *
     * @param message Poruka posle uspešnog izvršenja
     * @param server Ime servera za poruku o grešci
     * @param task Ime taks-a za poruku o grešci
     * @param postStoreEvent
     * @param msgHolder
     * @param errHolder
     */
    public void executeRemoteShellCommand(String message, String server, String task, ObjectHolder<String> msgHolder, ObjectHolder<Boolean> errHolder, Runnable postStoreEvent) {
        ExecutorService TE = Executors.newCachedThreadPool();

        TE.execute(() -> {
            Future<String> execResult = ES.submit(callable);

            try {
                String taskFinishedMsg = execResult.get();

                String msg = taskFinishedMsg == null || taskFinishedMsg.isEmpty() ? message : message + "\n" + taskFinishedMsg;
                msgHolder.set(msg);
                Logger.getLogger("#### UniTaskExecutor ---> ").log(Level.INFO, msg);
            } catch (InterruptedException | ExecutionException e) {
                String errorMsgHeader = String.format("Server [%s], task [%s]\n", server, task);
                Logger.getLogger("UniTaskExecutor Error.").log(Level.SEVERE, "Greška : {0}, {1}", new Object[]{errorMsgHeader, e.getMessage()});
                errHolder.set(true);
                msgHolder.set(errorMsgHeader + ", Greška : " + e.getMessage());
            }

            ES.shutdown();
            Objects.requireNonNull(postStoreEvent, getClass().getSimpleName() + " -> Obezbediti post-store kod.");
            postStoreEvent.run();
        });

        TE.shutdown();
    }
}
