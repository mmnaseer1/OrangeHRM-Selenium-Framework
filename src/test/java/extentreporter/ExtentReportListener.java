package extentreporter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import BasePackage.BaseTest;
import utilitiesPackage.UtilitiesClass;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("test-out/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		test.set(extent.createTest(result.getMethod().getMethodName())
				.assignCategory(result.getTestContext().getName()));
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
		try {
			String path = new UtilitiesClass(BaseTest.getDriver())
					.takeScreenShot(result.getMethod().getMethodName());
			
			if (path != null) {
				test.get().addScreenCaptureFromPath(path); 
			}
			
		} catch (Exception e) {
			test.get().warning("Could not attach screenshot: " + e.getMessage());
		}
	}
	
	 @Override
	    public void onTestSkipped(ITestResult result) {
	        if (test.get() == null) return;
	        String reason = (result.getThrowable() == null)
	                ? "Test skipped."
	                : "Test skipped. Reason: " + result.getThrowable().getMessage();
	        test.get().skip(reason); // <-- String overload works
	    }


	    @Override
	    public void onFinish(ITestContext context) {
	        if (extent != null) extent.flush();
	    }
	    
	    
	    public static void log(String message) {
	        if (test.get() != null) {
	            test.get().info(message);
	        }
	    }
}
