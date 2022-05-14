package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import listeners.TestListners;
import org.testng.Reporter;
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
        TestListners.extentTestThread.get().log(Status.INFO,"Getting auth by hitting post url");
        Response res  = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_AUTH),PropertyUtils.getProperty("auth"));
        System.out.println(res.asString());
        TestListners.extentTestThread.get().log(Status.INFO,"Fetching token from response using rest assured path");
        token = res.path("token");
        TestListners.extentTestThread.get().log(Status.INFO,"received auth tokenfor put and Delete command");
        System.out.println("received auth tokenfor put and Delete command -> "+token);
        System.out.println("===============================================");

    }

    @Test(dataProvider = "datafromproperty")
    public void CreateBooking(Object payload){
        TestListners.extentTestThread.get().log(Status.INFO,"hittin post to create booking");
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE),payload.toString());
        System.out.println(res.asString());
        id = res.path("bookingid");
        System.out.println("Created booking successfully with id using data provider-> " + id);
        System.out.println("===============================================");

    }

    @Test
    public void ModifyExistingPayload() throws IOException {
        TestListners.extentTestThread.get().log(Status.INFO,"Modifying existing payload from property files");
        String samplepayload = PropertyUtils.getProperty("samplepayload");
        TestListners.extentTestThread.get().log(Status.INFO,"Use DocumentContext from jayways to parse string");
        DocumentContext context = JsonPath.using(Configuration.builder().build()).parse(samplepayload);
        context.set("$.lastname","Test2");
        TestListners.extentTestThread.get().log(Status.INFO,"Upating values using set");
        updatedpayload = context.jsonString();
        System.out.println(updatedpayload);
        TestListners.extentTestThread.get().log(Status.INFO,"Details modified in sample payload from properties files using Jayways");
        System.out.println("Details modified in sample payload from properties files using Jayways");
        System.out.println("===============================================");

    }



    @Test(dependsOnMethods = {"CreateBooking","ModifyExistingPayload","getAuth"})
    public void UpdateBookingDetailsUsingPost(){
        TestListners.extentTestThread.get().log(Status.INFO,"Updating booking details using modified payload");
        Response res = RestFactory.PutRequest(CreateURL.getURL(EndPoints.PUT_RESOURCE),updatedpayload,id,token);
        System.out.println(res.asString());
        String lastname = res.path("lastname");
        TestListners.extentTestThread.get().log(Status.INFO,"Checking response time should be less then 1500L");

        res.then().time(Matchers.lessThan(1500L));
        System.out.println("time is less than 1500L ->  "+res.time());
        TestListners.extentTestThread.get().log(Status.PASS,"response time less then 1500L");
        System.out.println("Details updated in server with updated sample payload for 'lastname'->" + lastname );
        System.out.println("===============================================");
    }

    @Test(dependsOnMethods = {"UpdateBookingDetailsUsingPost","getAuth"})
    public void UpdatepartialBookingDetailsUsingPatch() throws IOException {
        TestListners.extentTestThread.get().log(Status.INFO,"sending partial data using patch");
        String patchpayload = PropertyUtils.getProperty("Patchpayload");
        String updatedpaylad = JsonUtils.stringToJson(patchpayload);
        TestListners.extentTestThread.get().log(Status.INFO,"taking help of string to Json to store string as json");
        Response res = RestFactory.PatchRequest(CreateURL.getURL(EndPoints.POST_RESOURCE),updatedpaylad,id,token);
        System.out.println("testtestteststetset   "+ res.asString());
        String lastname = res.path("lastname");
        TestListners.extentTestThread.get().log(Status.INFO,"Checking if lastname is 'Brown'");
        assertThat(lastname,equalToIgnoringCase("Brown"));
        System.out.println("Details updated in server with updated patch payload for 'lastname'->" + lastname );
        System.out.println(res.asString());
        System.out.println("===============================================");
    }

    @Test(dependsOnMethods = {"UpdatepartialBookingDetailsUsingPatch"})
    public void deleteBookingDetails() throws IOException {
        TestListners.extentTestThread.get().log(Status.INFO,"hitting delete request");
        Response res = RestFactory.deleteRequestCookie(CreateURL.getURL(EndPoints.DELETE_RESOURCE),id,token);
        assertThat(res.statusCode(),equalTo(201));
        System.out.println("Details deleted successfully for id ->" + id );
        TestListners.extentTestThread.get().log(Status.INFO,"Delete is successfull");
        System.out.println(res.asString());
        System.out.println("===============================================");
    }


    @Test(dependsOnMethods = {"deleteBookingDetails"})
    public void GetBookingDetailsfterdelete() throws IOException {
        TestListners.extentTestThread.get().log(Status.INFO,"Checking if deleted booking id still exist");

        Response res = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE),id);
        assertThat(res.statusCode(),equalTo(404));
        System.out.println(res.asString());
        TestListners.extentTestThread.get().log(Status.INFO,"deleted booking id doesn't exist, its a success got 404");
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
