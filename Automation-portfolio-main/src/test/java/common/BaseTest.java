package common;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class BaseTest {



		/* declare variables */
		public static String browser_name = "chrome" ;
		public static  WebDriver driver;
		public static WebDriverWait basedriver_wait ;
	    public static JavascriptExecutor js_executor ;


	 @BeforeSuite(description = "Initialise the webdriver based on browser pramater")
	    public  void launchBrowser() {

	    	/* Based on type of browser to run, run it for user*/
	    	if (browser_name == "chrome") {
	    		WebDriverManager.chromedriver().setup();
	    		driver = new ChromeDriver();
	    		basedriver_wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	    		js_executor = (JavascriptExecutor) driver;
	    	}else
	    	if (browser_name == "firefox") {
	    		WebDriverManager.firefoxdriver().setup();
	    		driver = new FirefoxDriver();
	    		basedriver_wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	    		js_executor = (JavascriptExecutor) driver;
	       }else
	       if  (browser_name == "edge"){
	         	WebDriverManager.edgedriver().setup();
	         	driver = new EdgeDriver();
	         	basedriver_wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	         	js_executor = (JavascriptExecutor) driver;
	       }
	    }





     
	   @AfterSuite(description = "All our test have ran, now close the browser, delete files..etc")
	    public  void TearDown() {
	    	Reporter.log(">>>>>>>>>>>>>>>Shutting down browser");
	    	try {
				Thread.sleep(5000);
				driver.quit();
			} catch (InterruptedException e) {
				Reporter.log(e.getMessage());
			}
	    	
	    }  
    

	}





