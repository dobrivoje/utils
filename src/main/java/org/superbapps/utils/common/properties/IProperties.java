package org.superbapps.utils.common.properties;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 *
 * @author dobri7@gmail.com
 */
public interface IProperties {

    static String MAVEN_ROOT = "src/main/resources/";

    void setPath(String path);

    void setPath(InputStream inputStream);

    String get(String key) throws FileNotFoundException;

    Map<String, String> getProperties() throws FileNotFoundException;

    void storeProperties(Map<String, String> properties) throws FileNotFoundException;

}
