package extentmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static ExtentReports report;

    public static ExtentReports getReport() {
        if (report == null)
            createReport();
            return report;
        }

    public static ExtentReports createReport () {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter( "extentreport\\extentreport1.html");
        htmlReporter.config().setDocumentTitle("Automation");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        report.setSystemInfo("User", "Automation tester");
        report.setSystemInfo("Platform", "Web");
        return report;
    }
}
