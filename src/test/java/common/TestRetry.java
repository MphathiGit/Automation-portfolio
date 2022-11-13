package common;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetry implements IRetryAnalyzer {

	private int retryCount = 0;
	private static final int maxRetyrCount = 2;



	@Override
	public boolean retry(ITestResult result) {
	 if(retryCount < maxRetyrCount) {
		 retryCount++;
		 return true;
	 }
		return false;
	}




}
