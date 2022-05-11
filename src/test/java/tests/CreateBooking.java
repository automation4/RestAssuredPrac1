/*
package tests;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.GetProperty;
import java.io.IOException;
import static io.restassured.RestAssured.given;



public class CreateBooking extends TestBase {

    static int id ;
    @Test(dataProvider = "getdata")
    public void createBooking(String key, String payload){
        System.out.println("key> "+key);
        System.out.println("payload> "+payload);

        Response res = given().spec(rs)
                .body(payload)
                .post();
        System.out.println(res.asString());
        id = res.path("bookingid");
        System.out.println("id -> "+id);
    }

    @DataProvider
    public Object[][] getdata() throws IOException {
        Object[][] data = GetProperty.getExcelData("samplepayload1");
        return data;
    }
}
*/
