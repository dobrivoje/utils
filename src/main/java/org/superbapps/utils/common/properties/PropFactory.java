package org.superbapps.utils.common.properties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class PropFactory implements IProperties {

    private static final Properties PROP = new Properties();
    private String path;
    private InputStream inputStream;

    public PropFactory() {
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setPath(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public synchronized void storeProperties(Map<String, String> properties) throws FileNotFoundException {
        OutputStream output = new FileOutputStream(this.path);

        properties.entrySet().stream().forEach((rec) -> {
            PROP.setProperty(rec.getKey(), rec.getValue());
        });

        try {
            PROP.store(output, null);
        } catch (IOException ex) {
            Logger.getLogger(PropFactory.class.getName()).log(Level.SEVERE, "ERROR : {0}", ex.getMessage());
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                Logger.getLogger(PropFactory.class.getSimpleName())
                        .log(Level.SEVERE, "ERROR : {0}", e.getMessage());
            }
        }
    }

    @Override
    public synchronized Map<String, String> getProperties() throws FileNotFoundException {
        Map<String, String> M = new HashMap<>();

        InputStream input = ClassLoader.getSystemResourceAsStream(this.path);

        try {
            PROP.load(input);

            for (Map.Entry<Object, Object> entry : PROP.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                M.put(key, value);
            }
        } catch (IOException e) {
            Logger.getLogger(PropFactory.class.getSimpleName())
                    .log(Level.SEVERE, "ERROR : {0}", e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                Logger.getLogger(PropFactory.class.getSimpleName())
                        .log(Level.SEVERE, "ERROR : {0}", e.getMessage());
            }
        }

        return M;
    }

    @Override
    public synchronized String get(String key) throws FileNotFoundException {
        InputStream input = ClassLoader.getSystemResourceAsStream(this.path);
        String res;

        try {
            PROP.load(input);
            res = (String) PROP.get(key);
        } catch (IOException e) {
            res = null;
            Logger.getLogger(PropFactory.class.getSimpleName())
                    .log(Level.SEVERE, "ERROR : {0}", e.getMessage());
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                Logger.getLogger(PropFactory.class.getSimpleName())
                        .log(Level.SEVERE, "ERROR : {0}", e.getMessage());
            }
        }

        return res;
    }

}
