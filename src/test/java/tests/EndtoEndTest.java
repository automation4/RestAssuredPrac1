package tests;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restclient.RestFactory;
import utils.CreateURL;
import utils.EndPoints;
import utils.JsonUtils;
import utils.PropertyUtils;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

public class EndtoEndTest {
  public String token;
  public String updatedpayload;
  public int id;

    @Test
    public void getAuth() throws IOException {
        Response res  = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_AUTH),PropertyUtils.getProperty("auth"));
        System.out.println(res.asString());
        token = res.path("token");
        System.out.println("received auth tokenfor put and Delete command -> "+token);
        System.out.println("===============================================");

    }

    @Test(dataProvider = "datafromproperty")
    public void CreateBooking(Object payload){
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE),payload.toString());
        System.out.println(res.asString());
        id = res.path("bookingid");
        System.out.println("Created booking successfully with id using data provider-> " + id);
        System.out.println("===============================================");

    }

    @Test
    public void ModifyExistingPayload() throws IOException {
        String samplepayload = PropertyUtils.getProperty("samplepayload");
        DocumentContext context = JsonPath.using(Configuration.builder().build()).parse(samplepayload);
        context.set("$.lastname","Test2");
         updatedpayload = context.jsonString();
        System.out.println(updatedpayload);
        System.out.println("Details modified in sample payload from properties files using Jayways");
        System.out.println("===============================================");

    }



    @Test(dependsOnMethods = {"CreateBooking","ModifyExistingPayload","getAuth"})
    public void UpdateBookingDetailsUsingPost(){
        Response res = RestFactory.PutRequest(CreateURL.getURL(EndPoints.PUT_RESOURCE),updatedpayload,id,token);
        System.out.println(res.asString());
        String lastname = res.path("lastname");
        res.then().time(Matchers.lessThan(1500L));
        System.out.println("time is less than 1500L ->  "+res.time());
        System.out.println("Details updated in server with updated sample payload for 'lastname'->" + lastname );
        System.out.println("===============================================");
    }

    @Test(dependsOnMethods = {"UpdateBookingDetailsUsingPost","getAuth"})
    public void UpdatepartialBookingDetailsUsingPatch() throws IOException {
        String patchpayload = PropertyUtils.getProperty("Patchpayload");
        String updatedpaylad = JsonUtils.stringToJson(patchpayload);
        Response res = RestFactory.PatchRequest(CreateURL.getURL(EndPoints.POST_RESOURCE),updatedpaylad,id,token);
        System.out.println("testtestteststetset   "+ res.asString());
        String lastname = res.path("lastname");
        assertThat(lastname,equalToIgnoringCase("Brown"));
        System.out.println("Details updated in server with updated patch payload for 'lastname'->" + lastname );
        System.out.println(res.asString());
        System.out.println("===============================================");
    }

    @Test(dependsOnMethods = {"UpdatepartialBookingDetailsUsingPatch"})
    public void deleteBookingDetails() throws IOException {
        Response res = RestFactory.deleteRequestCookie(CreateURL.getURL(EndPoints.DELETE_RESOURCE),id,token);
        assertThat(res.statusCode(),equalTo(201));
        System.out.println("Details deleated successfully for id ->" + id );
        System.out.println(res.asString());
        System.out.println("===============================================");
    }


    @Test(dependsOnMethods = {"deleteBookingDetails"})
    public void GetBookingDetailsfterdelete() throws IOException {
        Response res = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE),id);
        assertThat(res.statusCode(),equalTo(404));
        System.out.println(res.asString());
        System.out.println("===============================================");
    }

    @DataProvider(name ="datafromproperty")
    public Object[][] getDataFromProperty() throws IOException {
        String data = PropertyUtils.getProperty("samplepayload");
        Object[][] data1 = new Object[1][1];
        for(int i=0;i<data1.length;i++){
            for(int j=0;j<data1[i].length;j++){
                data1[i][j] = data;
            }
        }
        return data1 ;
    }
}
