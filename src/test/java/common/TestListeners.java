package common;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import Utilities.Utils;



public class TestListeners extends Utils implements ITestListener {



	@Override
	public void onTestStart(ITestResult result) {
		getScreenshot();
		Reporter.log("Test Starting...");
		Reporter.log("Method name is - "+result.getName());
	}



@Override
public void onTestSuccess(ITestResult result) {
	   getScreenshot();
	   Reporter.log("Outcome of test execution is - "+result.getStatus());
	}



@Override
public void onTestFailure(ITestResult result) {
	   getScreenshot();
	   Reporter.log("Outcome of test execution is - "+result.getStatus());
  }







@Override
public void onTestSkipped(ITestResult result) {
	 //TODO
   }


@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	//TODO
   }




}
