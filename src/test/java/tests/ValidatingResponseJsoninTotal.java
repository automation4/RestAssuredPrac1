/*
package tests;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;
import utils.GetProperty;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ValidatingResponseJsoninTotal extends TestBase {

    int id;
    @Test
    public void validatingResponseValueforBookingid() throws IOException, JSONException {
        id = GetProperty.getID("[0].bookingid",rs); //rest assuring jsonpath
        String acutalresponse = given().spec(rs).get("/booking/645").then().extract().response().asString();
        String expectedresponse = GetProperty.jsonToString("valuecheckjson.json");
        JSONAssert.assertEquals(expectedresponse, acutalresponse, JSONCompareMode.STRICT);
        System.out.println("Values are correct");
    }
}
*/
