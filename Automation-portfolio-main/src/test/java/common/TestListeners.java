package common;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import Utilities.Utils;



public class TestListeners extends Utils implements ITestListener {



	public void onTestStart(ITestResult result) {
		getScreenshot();
		Reporter.log("Test Starting...");
		Reporter.log("Method name is - "+result.getName());
	}



public void onTestSuccess(ITestResult result) {
	   getScreenshot();
	   Reporter.log("Outcome of test execution is - "+result.getStatus());
	}



public void onTestFailure(ITestResult result) {
	   getScreenshot();
	   Reporter.log("Outcome of test execution is - "+result.getStatus());
  }







public void onTestSkipped(ITestResult result) {
	 //TODO
   }


public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	//TODO
   }




}
