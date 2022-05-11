package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.Bookingresponsepojo;
import restclient.RestFactory;
import utils.CreateURL;
import utils.StringConstants;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class BookingDetailsPojo {

    int id = 0;

    @Test
    public void getBookingidviaPojo() {
        Response res = RestFactory.getRequest(CreateURL.getBaseURI("1"));
        System.out.println(res.asString());
       /* System.out.println(given().spec(rs).get("/booking/" + id).then().log().all().extract().response().asString()); //set reponse value in class

        Bookingresponsepojo bpjo = given().spec(rs).get("/booking/" + id).then().extract().response().as(Bookingresponsepojo.class); //set reponse value in class
        System.out.println(bpjo.getFirstname());
        System.out.println(bpjo.getAdditionalneeds());
        System.out.println(bpjo.getBookingdates().getCheckin());
        System.out.println(bpjo.getBookingdates().getCheckout());
        System.out.println(bpjo.getLastname());
        System.out.println(bpjo.getTotalprice());
        assertThat(bpjo.getFirstname(), equalToIgnoringCase("julia")); //using hamcrest for valiation single value
    }*/

    }
}
