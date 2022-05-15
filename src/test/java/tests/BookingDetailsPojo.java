package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import listeners.TestListners;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.Bookingresponsepojo;
import org.hamcrest.Matchers;

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static restclient.RestFactory.*;
import static utils.CreateURL.*;
import static utils.EndPoints.*;
@Listeners(TestListners.class)
public class BookingDetailsPojo {

    int id = 0;
    @Test
    public void getBookingidviaPojo() {

        SoftAssert softly  = new SoftAssert();
        Response res1 = getRequest(getURL(GET_RESOURCE));
        TestListners.extentTestThread.get().log(Status.INFO,"Getting Booking details via POJO");
        System.out.println(res1.asString());
        id = res1.path("[0].bookingid");
        Response res2 = getRequest(getURL(GET_RESOURCE),id);
        System.out.println(res2.asString());

        TestListners.extentTestThread.get().log(Status.INFO,"set reponse value in class");
        Bookingresponsepojo bpjo = getRequest(getURL(GET_RESOURCE),id).then().extract().response().as(Bookingresponsepojo.class);
        System.out.println(bpjo.getFirstname());
        System.out.println(bpjo.getAdditionalneeds());
        System.out.println(bpjo.getBookingdates().getCheckin());
        System.out.println(bpjo.getBookingdates().getCheckout());
        System.out.println(bpjo.getLastname());
        System.out.println(bpjo.getTotalprice());

        TestListners.extentTestThread.get().log(Status.INFO,"using hamcrest for valiation single value");
        softly.assertEquals(bpjo.getLastname(),"Davis");

        //Checking Schema
        TestListners.extentTestThread.get().log(Status.INFO,"Checking Schema");
        softly.assertEquals(res2.asString(),JsonSchemaValidator.matchesJsonSchemaInClasspath("schemacheck.json"));
        System.out.println("Schema is correct");
        TestListners.extentTestThread.get().log(Status.INFO,"Schema is correct");

        //Checking responsetime
        TestListners.extentTestThread.get().log(Status.INFO,"Checking responsetime");
        res2.then().time(Matchers.lessThan(2000L));
        System.out.println("responsetime is correct within 2000L");
        TestListners.extentTestThread.get().log(Status.INFO,"responsetime is correct within 2000L");

        //asserting values
        TestListners.extentTestThread.get().log(Status.INFO,"asserting values");
        int totalprice = res2.path("totalprice");
        softly.assertEquals(totalprice,174);
        TestListners.extentTestThread.get().log(Status.INFO,"total matches to 174");
        TestListners.extentTestThread.get().log(Status.INFO,"total matches to 174");
        softly.assertAll();
    }

}

