package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import listeners.TestListners;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pojo.Bookingresponsepojo;
import org.hamcrest.Matchers;

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static restclient.RestFactory.*;
import static utils.CreateURL.*;
import static utils.EndPoints.*;

public class BookingDetailsPojo {

    int id = 0;

    @Test
    public void getBookingidviaPojo() {
        Response res1 = getRequest(getURL(GET_RESOURCE));
        ExtentTest test = TestListners.extentTestThread.get();
        test.log(Status.INFO,"Getting Booking details via POJO");
        System.out.println(res1.asString());
        id = res1.path("[0].bookingid");
        Response res2 = getRequest(getURL(GET_RESOURCE),id);
        System.out.println(res2.asString());

        test.log(Status.INFO,"set reponse value in class");
        Bookingresponsepojo bpjo = getRequest(getURL(GET_RESOURCE),id).then().extract().response().as(Bookingresponsepojo.class);
        System.out.println(bpjo.getFirstname());
        System.out.println(bpjo.getAdditionalneeds());
        System.out.println(bpjo.getBookingdates().getCheckin());
        System.out.println(bpjo.getBookingdates().getCheckout());
        System.out.println(bpjo.getLastname());
        System.out.println(bpjo.getTotalprice());

        test.log(Status.INFO,"using hamcrest for valiation single value");
        assertThat(bpjo.getLastname(), equalToIgnoringCase("Davis"));

        //Checking Schema
        test.log(Status.INFO,"Checking Schema");
        assertThat(res2.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemacheck.json"));
        System.out.println("Schema is correct");

        //Checking responsetime
        test.log(Status.INFO,"Checking responsetime");
        res2.then().time(Matchers.lessThan(2000L));
        System.out.println("responsetime is correct within 2000L");

        //asserting values
        test.log(Status.INFO,"asserting values");
        assertThat(res2.path("totalprice"),equalTo(174));
        test.log(Status.INFO,"total matches to 174");

    }

}

