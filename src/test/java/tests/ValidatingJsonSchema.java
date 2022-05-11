/*
package tests;

import org.testng.annotations.Test;
import utils.GetProperty;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class ValidatingJsonSchema extends TestBase {
    int id;
    @Test
    public void validatingResponseSchemagforBookingid() throws IOException {
        id = GetProperty.getID("[0].bookingid",rs); //rest assuring jsonpath
        String reponseasstring = given().spec(rs).get("/booking/" + id).then().extract().response().asString();
        assertThat(reponseasstring, matchesJsonSchemaInClasspath("schemacheck.json")); //https://www.liquid-technologies.com/online-json-to-schema-converter
        System.out.println("Schema is correct");
    }
}
*/
