
package tests;
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
        id = res.path("[0].bookingid");

        //asserting Status code
        assertThat(res.statusCode(), equalTo(200));
        System.out.println("id fetched -> "+ id);
    }

}

