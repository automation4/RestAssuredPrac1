
package tests;

import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;
import restclient.RestFactory;
import utils.CreateURL;
import utils.EndPoints;
import utils.JsonUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ValidatingResponseJsoninTotal {

    int id;
    @Test
    public void validatingResponseValueforBookingid() throws IOException, JSONException {
        Response res = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE));
        id = res.path("[0].bookingid");
        String acutalresponse = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE),id).asString();
        String expectedresponse = JsonUtils.jsonToString("valuecheckjson.json");
        JSONAssert.assertEquals(expectedresponse, acutalresponse, JSONCompareMode.STRICT);
        System.out.println("Values are correct");
    }
}
