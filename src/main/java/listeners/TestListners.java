package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import extentmanager.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListners implements ITestListener {

    ExtentReports report = ExtentManager.getReport();
    public static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
        ExtentTest test =  report.createTest(result.getTestClass().getName() +"->" +result.getMethod().getMethodName());
        extentTestThread.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        extentTestThread.get().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {
        extentTestThread.get().log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
        extentTestThread.get().log(Status.WARNING, result.getThrowable());
    }
    public void onTestSkipped(ITestResult result) {
        extentTestThread.get().log(Status.SKIP, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.ORANGE));

    }

    public  void onFinish(ITestContext context) {
        if(report != null){
            report.flush();
                   }
    }
}
