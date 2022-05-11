package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToString {

    public static String jsonToString(String filename) throws IOException {
        String filepath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + filename;
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }
}
