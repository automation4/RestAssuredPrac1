package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {

    public static String getProperty(String key) throws IOException {
       FileInputStream propertyfilepath = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\testdata.properties");
        Properties prop = new Properties();
        prop.load(propertyfilepath);
        return prop.getProperty(key);
    }



}
