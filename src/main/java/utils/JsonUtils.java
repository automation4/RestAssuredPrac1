package utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    public static String jsonToString(String filename) throws IOException {
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + filename;
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    public static String stringToJson(String payload) throws IOException {
        DocumentContext  context = JsonPath.using(Configuration.builder().build()).parse(payload);
        return context.jsonString();
    }

}
