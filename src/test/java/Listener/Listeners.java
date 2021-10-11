package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.invoice_portal.BaseTest.TestBase;

public class Listeners extends TestBase implements ITestListener {


	@Override
	   public void onTestStart(ITestResult result) {
	    // not implemented
	  }

	 
	@Override
	 public void onTestSuccess(ITestResult result) {
	    // not implemented
		System.out.println("Ops Test is Sacces");
	  }

	  
	@Override
	  public void onTestFailure(ITestResult result ) {
	    // not implemented
		System.out.println("Ops Test is Faile");
		GetScreenshot(result.getMethod().getMethodName());
	  }

	  
	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	 
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	 
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  
	@Override
	public void onStart(ITestContext context) {
	    // not implemented
	  }

	  
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
	  }
	
}
