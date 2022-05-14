package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    public static String getProperty(String key) throws IOException {
       FileInputStream propertyfilepath = new FileInputStream(System.getProperty("user.dir") + File.separator+ "src" + File.separator+"main" + File.separator+"resources" + File.separator+"testdata.properties");
        Properties prop = new Properties();
        prop.load(propertyfilepath);
        return prop.getProperty(key);
    }



}
