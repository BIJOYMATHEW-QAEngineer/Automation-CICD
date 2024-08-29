package testngFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import testngProjectFramwork.resourses.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest Test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest>extentTest = new ThreadLocal  <ExtentTest>(); // Thread safe

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Test = extent.createTest(result.getMethod().getMethodName());

		extentTest.set(Test); // unique tread ID
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Test.log(Status.PASS, "Test Pass");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// Test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());

		// step 1: Take screenshot
		// Step 2: Attach to report

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	//	Test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();

	}

}
