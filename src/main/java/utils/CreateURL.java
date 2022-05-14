package utils;

public class CreateURL{

    private final static String baseURI = "https://restful-booker.herokuapp.com";

    public static String getURL(String resourcePath){
        return baseURI + resourcePath;
    }
}


