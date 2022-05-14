package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import listeners.TestListners;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class  CommonTests {

        
    @Test(groups = "smoke")
    @Parameters({"name","city"})
    public void Test1(String name,String city){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test1 ");
        System.out.println("this is Test1");
        System.out.println("name ->"+name);
        System.out.println("city ->"+city);
    }

    @Test(groups = {"smoke"})
    public void Test2(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test2");
        System.out.println("this is Test2 smoke");
    }

    @Test(groups = "smoke")
    public void Test3(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test3 ");

        System.out.println("this is Test3");
    }
    @Test(groups = {"smoke","regression"})
    public void Test4(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test4 ");

        System.out.println("this is Test4 smoke");
    }
    @Test(groups = "smoke")
    public void Test5(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test5 ");

        System.out.println("this is Test5");
    }


    @Test(groups = "smoke",invocationCount = 4)
    public void Test6(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test6 ");

        System.out.println("this is Test6 smoke");
    }

    @Test(groups = "smoke")
    public void Test7(){
        TestListners.extentTestThread.get().log(Status.INFO,"Running Test7 ");

        int i =2;
            int c = 2 / 0;

    }
}
