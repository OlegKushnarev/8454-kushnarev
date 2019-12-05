package reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieReader {
    Class clazz;
    String fullFileName;

    public PropertieReader(Class clazz, String fullFileName) {
        this.clazz = clazz;
        this.fullFileName = this.fullFileName;
    }

    public int read(String nameProperty) throws IOException {
        Properties properties = new Properties();
        InputStream propertiesStream = this.clazz.getResourceAsStream("/server.properties");
        if (propertiesStream != null) {
            properties.load(propertiesStream);
        }
        return Integer.parseInt(properties.getProperty(nameProperty));
    }
}
