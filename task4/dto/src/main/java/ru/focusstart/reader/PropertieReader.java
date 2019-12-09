package ru.focusstart.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieReader {
    Class clazz;
    String fullFileName;

    public PropertieReader(Class clazz, String fullFileName) {
        this.clazz = clazz;
        this.fullFileName = fullFileName;
    }

    public String read(String nameProperty) throws IOException {
        Properties properties = new Properties();
        InputStream propertiesStream = this.clazz.getResourceAsStream(fullFileName);
        if (propertiesStream != null) {
            properties.load(propertiesStream);
        }
        return properties.getProperty(nameProperty);
    }
}
