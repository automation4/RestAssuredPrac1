
package tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import listeners.TestListners;
import org.testng.Reporter;
import restclient.RestFactory;
import utils.CreateURL;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class BookingDetailsValidations {
    int id;

    @Test
    public void validateStatusCode() {
        Response res = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE));
        ExtentTest test = TestListners.extentTestThread.get();
        test.log(Status.INFO,"This test validate Status code");
        test.log(Status.INFO,"Getting Booking id");

        id = res.path("[0].bookingid");

        //asserting Status code
        test.log(Status.INFO,"Performing assertion");

        assertThat(res.statusCode(), equalTo(200));
        System.out.println("id fetched -> "+ id);


    }

}

