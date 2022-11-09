package ui;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import Utilities.Utils;
import common.BaseTest;

public class SearchResultsPage extends BaseTest {


    private static String CheckoutDateXpath    = "//button[@data-testid= 'date-display-field-end']";
    private static String FromDate_xpath   =  "//span[@data-date ='2022-11-09']";
    private static String ToDate_xpath   = "//span[@data-date ='2022-12-05']";
    private static String  Search_button_xpath = "//button[@type= 'submit']";

    private static String discount_price_xpath = "//*[contains(text(),'Original price ZAR')]";
    private static String hotel_fee_xpath = "//span[starts-with(@class,'fcab')]";
    private static ArrayList<WebElement> hotel_fee_list = (ArrayList<WebElement>) driver.findElements(By.xpath(hotel_fee_xpath));

    private static String next_page_button_xpath ="//button[@aria-label=' 1']";
    private static String  show_price_button_xpath = "//button[@aria-expanded = 'false']";
  	private static ArrayList<WebElement> hotel_prices ;
  	private static double booking_price = 0;
    private static String current_url = "";
    private static String old_url = driver.getCurrentUrl();
 
    
    
    
    
    
    

    /**
     * Scroll until you are at the result that matches the price Range we suppied, then click on it
	 */
 	public static String scrollAndFind(int fromPrice,int toPrice) {
 		String priceFound =  " " ;
 		for (int row=0;row < hotel_fee_list.size();row++) {
 			  basedriver_wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hotel_fee_xpath)));
 	 	  	  js_executor.executeScript("arguments[0].scrollIntoView(true);", hotel_fee_list.get(row));
              try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
                Reporter.log(e.getMessage());
			}
 	 	  	  System.out.println(hotel_fee_list.get(row).getText());
 	 	  	 hotel_prices = (ArrayList<WebElement>) driver.findElements(By.xpath(hotel_fee_xpath));
 	 	  	  
 	 	  	  booking_price = castStringPriceToInt(row);
 	 	  	  System.out.println(">>Converted price: R"+booking_price);

 	 	  	  if (booking_price > fromPrice && booking_price < toPrice) {
 	 	  		  BaseTest.js_executor.executeScript("window.scrollBy(0, -250);");
 	 	  		  priceFound = getAllAvailableButtElements().get(row).toString();
 	 	          getAllAvailableButtElements().get(row).click();
 	 	  		  basedriver_wait.until(ExpectedConditions.numberOfWindowsToBe(2));
 	 	  		  Utils.switchToLatestTab();
 	 	  		  current_url = driver.getCurrentUrl();

 	 	  		  System.out.println(">>>>>>>Now thats my kinda deal and Price<<<<<<<<): R"+booking_price);
 	 	  		  break;
 	 	  	  }
 	 	    }
 		return priceFound;
 	}





 	/**
     * Cast String price to Double
	 */
 	public static double castStringPriceToInt(int row) {
 		 if ( !(hotel_prices.get(row)).getText().substring(0,2).equals("ZAR") ) {
 			 System.out.println("convert: "+ hotel_prices.get(row).getText());
 		  	   return Double.parseDouble((hotel_prices.get(row)).getText().substring(4).replace(",",""));
 		   }
 		 return 0;
 	}





	/**
	 *
	 * get all the "See Availability" buttons on this page
	 */
	public static ArrayList<WebElement> getAllAvailableButtElements(){
		return  (ArrayList<WebElement>)driver.findElements(By.xpath("//*[contains(text(),'See availability')]"));
	}



    /**
	 * click on date field and then do calendar selection(currenlty hardocded dates)
	 */
	public static void  checkinDateSelectionAndSearch() {
			basedriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FromDate_xpath)));
			getFromDateCalendarElement().click();
			getCheckoutDate().click();
			basedriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ToDate_xpath)));
	        getToDateCalendarElement().click();
	}




	/**
	 * use JavaScript executor to run JavScrpt code to verify status of pageLoad
	 */
	 public static boolean pageIsReady() {
		 return js_executor.executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
	 }



	 
	 /**
     * Get cached URL(prior to current one)
	 */
	public static String getOldWindowUrl() {
		return old_url;
	}





	 
	/**
     * Return checkout date element
	 */
	public static WebElement getCheckoutDate() {
		return driver.findElement(By.xpath(CheckoutDateXpath)) ;
	}


	
	/**
     * Return searchButton  element
	 */
	public static WebElement getSearchButtonElement() {

		return driver.findElement(By.xpath(Search_button_xpath));
	}



	/**
	 *
	 * get next page button icon at screen bottom
	 */
	public static WebElement getNextPageElement() {
		return driver.findElement(By.xpath(next_page_button_xpath));
	}

	
	
	/**
     * Return ShowPriceButton  element
	 */
    public static WebElement getShowPriceButton() {
	return driver.findElement(By.xpath(show_price_button_xpath));
}



    /**
     * Return discountPrice  element
	 */
    public static WebElement getDiscountPriceElement() {
	  return driver.findElement(By.xpath(discount_price_xpath));
    }


	/**
	 *
	 * get the current window's URL
	 */
	public static String getCurrentWindowUrl() {
		return current_url;
	}




	public static WebElement getToDateCalendarElement() {
		return driver.findElement(By.xpath(ToDate_xpath));
	}


	public static WebElement getFromDateCalendarElement() {
		return driver.findElement(By.xpath(FromDate_xpath));
	}





}
