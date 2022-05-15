
package tests;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import listeners.TestListners;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restclient.RestFactory;
import utils.*;
import java.io.IOException;


public class CreateBooking {
    static int id;
    @Test(dataProvider = "datafromexcel")
    public void createBookingusingExcel(Object payload) {
        TestListners.extentTestThread.get().log(Status.INFO,"Getting Payload from Excel");

        System.out.println("Paylod -> " + payload);
        TestListners.extentTestThread.get().log(Status.INFO,"Hitting post request");
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE), payload.toString());
        System.out.println(res.asString());
        TestListners.extentTestThread.get().log(Status.INFO,"Getting booking id");
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
        TestListners.extentTestThread.get().log(Status.INFO,"Getting Payload from Property files");

        System.out.println("Paylod -> " + payload);
        Response res = RestFactory.postRequest(CreateURL.getURL(EndPoints.POST_RESOURCE), payload.toString());
        System.out.println(res.asString());
        TestListners.extentTestThread.get().log(Status.INFO,"Getting booking id");
        id = res.path("bookingid");
        System.out.println("id -> " + id);
    }

    @DataProvider(name ="datafromproperty")
    public Object[][] getDataFromProperty() throws IOException {
        TestListners.extentTestThread.get().log(Status.INFO,"returning  Payload from Property files to test");

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
