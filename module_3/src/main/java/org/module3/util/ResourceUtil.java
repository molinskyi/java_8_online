package org.module3.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {
    private ResourceUtil() {}

    public static Map<String, String> getResources(ClassLoader classLoader) {
        try(InputStream inputStream = classLoader.getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                map.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
