package restclient;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class RestFactory {


    public static Response getRequest(String requestURI){
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(requestURI);
        return response;
    }
    public static Response getRequest(String requestURI,int id){
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(requestURI+"/"+id);
        return response;
    }
    public static Response deleteRequestCookie(String requestURI,int id,String token){
        RequestSpecification requestSpecification = given();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.header("Cookie","token="+token);
        Response response = requestSpecification.delete(requestURI+"/"+id);
        return response;
    }
    public static Response deleteRequestBsicAuth(String requestURI,int id){
        RequestSpecification requestSpecification = given();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.header("Authorisation","Basic YWRtaW46cGFzc3dvcmQxMjM=");
        Response response = requestSpecification.delete(requestURI+"/"+id);
        return response;
    }

    public static Response postRequest(String requestURI, String payload){
        RequestSpecification requestSpecification = given().body(payload);
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.post(requestURI);
        return response;
    }

    public static Response PutRequest(String requestURI, String payload,int id,String token){
        RequestSpecification requestSpecification = given().body(payload);
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.header("Accept","application/json");
        requestSpecification.header("Cookie","token="+token);
        Response response = requestSpecification.put(requestURI+"/"+id);
        return response;
    }

    public static Response PatchRequest(String requestURI, String patchpayload,int id,String token){
        RequestSpecification requestSpecification = given().body(patchpayload);
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.header("Accept","application/json");
        requestSpecification.header("Cookie","token="+token);
        Response response = requestSpecification.patch(requestURI+"/"+id);
        return response;
    }
}
