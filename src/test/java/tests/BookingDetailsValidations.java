/*
package tests;
import utils.GetProperty;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class BookingDetailsValidations extends TestBase {
    int id;

    @Test
    public void validateStatusCode() {
        Response res = given().spec(rs).get("/booking");
        id = GetProperty.getID("[0].bookingid",rs); //rest assuring jsonpath
        assertThat(res.statusCode(), equalTo(200));
        System.out.println("id fetched -> "+ id);
    }

}
*/
