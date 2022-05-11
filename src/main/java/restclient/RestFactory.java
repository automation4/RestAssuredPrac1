package restclient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestFactory {


    public static Response getRequest(String requestURI){
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(requestURI);
        return response;
    }


    public static Response postRequest(String requestURI, String payload){
        RequestSpecification requestSpecification = given().body(payload);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.post(requestURI);
        return response;
    }
}
