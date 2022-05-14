
package tests;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restclient.RestFactory;
import utils.*;
import java.io.IOException;


public class CreateBooking {

    static int id;
    @Test(dataProvider = "datafromexcel")
    public void createBookingusingExcel(Object payload) {
        System.out.println("Paylod -> " + payload);
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE), payload.toString());
        System.out.println(res.asString());
        id = res.path("bookingid");
        System.out.println("id -> " + id);
    }


    @DataProvider(name ="datafromexcel")
    public Object[][] getDatafromexcel() throws IOException {
        Object[][] data = ExcelUtils.getExcelData("samplepayload1");
       /* for (int i = 0; i < 1; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.println(data[i][j]);
            }
        }*/
        return data;
    }


    @Test(dataProvider = "datafromexcel")
    public void createBookingusingProperty(Object payload) {
        System.out.println("Paylod -> " + payload);
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE), payload.toString());
        System.out.println(res.asString());
        id = res.path("bookingid");
        System.out.println("id -> " + id);
    }

    @DataProvider(name ="datafromproperty")
    public Object[][] getDataFromProperty() throws IOException {
        String data = PropertyUtils.getProperty("samplepayload");
        Object[][] data1 = new Object[1][1];
        for(int i=0;i<data1.length;i++){
           for(int j=0;j<data1[i].length;j++){
               data1[i][j] = data;
           }
       }
        return data1 ;
    }



}
