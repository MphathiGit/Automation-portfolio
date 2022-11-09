package Utilities;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import common.BaseTest;


public  class Utils extends BaseTest{



    /*method to verify if element is REALLY REALLY in the viewpoint(visible to the eye)*/
    public  static Boolean isVisibleInViewport(WebElement element, WebDriver driver) {
	      return (Boolean)((JavascriptExecutor)driver).executeScript(
	      "var elem = arguments[0],                 " +
	      "  box = elem.getBoundingClientRect(),    " +
	      "  cx = box.left + box.width / 2,         " +
	      "  cy = box.top + box.height / 2,         " +
	      "  e = document.elementFromPoint(cx, cy); " +
	      "for (; e; e = e.parentElement) {         " +
	      "  if (e === elem)                        " +
	      "    return true;                         " +
	      "}                                        " +
	      "return false;                            "
	      , element);
	   }


    
    

	 /*get InnerText of element in VIEW (eye view) */
    public static String  getTextFromElementInView (WebElement element, WebDriver driver) {
	   if (isVisibleInViewport(element, driver)) {
		  return element.getText().toString();
	   }else {
		  return "";
	   }
    }




	/*scroll by 50units until the last Element supplied search result is in View to the human EYES*/
	public static void scrollPage(String direction, WebDriver driver,WebElement stopAt)  {
        while (true) {
        	if(direction == "DOWN") {
        		((JavascriptExecutor)BaseTest.driver).executeScript("window.scrollBy(0, 250)");
        	}else {
        		((JavascriptExecutor)BaseTest.driver).executeScript("window.scrollBy(0, -250)");
        	}

      	  try {
			Thread.sleep(3000);
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
      	  if(isVisibleInViewport(stopAt,driver)) {
      		  break;
      	  }
        }
	}




	/*scroll to bottom of window*/
	public static void scrollToBottom() {
		((JavascriptExecutor)BaseTest.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	
	
    /*scroll to page TOP*/
	public static void scrollToTop() {
		((JavascriptExecutor)BaseTest.driver).executeScript("window.scrollTo(0, 0);");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}





	/*switch to latest opened TAB*/
	public static void switchToLatestTab() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 Set<String> windowHandle_list = BaseTest.driver.getWindowHandles();

		    for (Iterator<String> iterator = windowHandle_list.iterator(); iterator.hasNext(); ) {
		        String handle = iterator.next();
		        if(!iterator.hasNext()) {
		        	BaseTest.driver.switchTo().window(handle);
		        }
		    }
	}




	/*take screenshot*/
	public void getScreenshot() {
		Date currentDate = new Date();
		String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile,new File(".//screenshots//" + screenshotFileName+".JPEG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	  
	
	/*close current TAB/window*/
	public static void closeCurrentTab() {
		while(isPageReady() == false) {
			 try {
				  basedriver_wait.wait();
			} catch (InterruptedException e) {
                  Reporter.log(e.getMessage());
			}	
			 if(isPageReady() == true) {
			     driver.close();
				 break;
			 }
		}	
	}

 

	/*check if page load finished*/
	 private  static boolean isPageReady() {
		 return (js_executor.executeScript("return document.readyState").equals("complete"));
	 }

	 
	 

}
