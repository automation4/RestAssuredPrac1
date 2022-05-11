package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class  CommonTests {

    @Test(groups = "regression")
    @Parameters({"name","city"})
    public void Test1(String name,String city){
        System.out.println("this is Test1");
        System.out.println("name ->"+name );
        System.out.println("city ->"+city );

    }

    @Test(groups = {"smoke","regression"})
    public void Test2(){
        System.out.println("this is Test2 smoke");
    }

    @Test(groups = "regression")
    public void Test3(){
        System.out.println("this is Test3");
    }
    @Test(groups = {"smoke","regression"})
    public void Test4(){
        System.out.println("this is Test4 smoke");
    }
    @Test(groups = "regression")
    public void Test5(){
        System.out.println("this is Test5");
    }


    @Test(groups = "smoke",invocationCount = 4)
    public void Test6(){
        System.out.println("this is Test6 smoke");
    }

}
