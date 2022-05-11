package utils;

public class CreateURL{

    private final static String baseURI = "https://restful-booker.herokuapp.com";

    public static String getBaseURI(){
        return baseURI;
    }

    public static String getBaseURI(String resourcePath){
        return baseURI + resourcePath;
    }
}


