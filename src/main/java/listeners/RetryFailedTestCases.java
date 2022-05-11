package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

    public int count =0;
    public int limit =2;


    @Override
    public boolean retry(ITestResult iTestResult) {
        boolean value = count<limit;
            count++;
        return value;
    }
}
