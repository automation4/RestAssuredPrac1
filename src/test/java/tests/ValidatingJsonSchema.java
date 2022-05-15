
package tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import restclient.RestFactory;
import utils.CreateURL;
import utils.EndPoints;

import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;


public class ValidatingJsonSchema {
    int id;
    @Test
    public void validatingResponseSchemagforBookingid() throws IOException {
        Response res = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE));
        id = res.path("[0].bookingid");
        Response res1 = RestFactory.getRequest(CreateURL.getURL(EndPoints.GET_RESOURCE),id);
        String responsetovalidate = res1.asString();
        assertThat(responsetovalidate, JsonSchemaValidator.matchesJsonSchemaInClasspath("schemacheck.json")); //https://www.liquid-technologies.com/online-json-to-schema-converter
        Reporter.log("Schema is correct");
    }
}

