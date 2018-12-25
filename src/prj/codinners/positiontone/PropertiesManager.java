package prj.codinners.positiontone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private String path;

    private Properties properties;

    public PropertiesManager(String path) {
        this.path = path;

        init();
    }

    private void init() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
