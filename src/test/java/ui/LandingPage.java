package ui;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import common.BaseTest;

public class LandingPage extends BaseTest{


	    private static String booking_webst_url = "https://booking.com";
	    public static  String header_xpath = "//span[@aria-label='Booking.com Online Hotel Reservations']";
	    private static String next_page_button_xpath ="//button[@aria-label=' 1']";
	    private static String Destination_field_Xpath   = "//input[@type = 'search' and @name='ss']";
	    private static String Search_button_xpath = "//span[@class='js-sb-submit-text ']";
	    private static String MonthYearXpath = "//div[@class='bui-calendar__month']";
        private static String  departDateFieldXpath = "//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb']";

           /*Month Year badges here*/
	   List<WebElement> monthYearBadges = driver.findElements(By.xpath(MonthYearXpath));



	    public static void clickOnDateField() throws InterruptedException {
	    	//click date text field

	        driver.findElement(By.xpath(departDateFieldXpath)).click();
	        Thread.sleep(3000);
	    }




	     /*This method is for entering the fromDate and Travel toDate*/
	    public static void EnterFromToDate(String fromMonth,String fromYear,String fromDay,String toMonth,String toYear, String toDay) {
	    	//basedriver_wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MonthYearXpath)));
	    	enterFromDate(fromMonth,fromYear,fromDay,toMonth,toYear, toDay);
	    	enterTodate(fromMonth,fromYear,fromDay,toMonth,toYear, toDay);
	    }





	   /*This method is a helper for the  method(EnterFromToDate)*/
	    private static void enterTodate(String fromMonth,String fromYear,String fromDay,String toMonth,String toYear, String toDay) {

	        basedriver_wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(MonthYearXpath))));

		/*Month Year badges here*/
		List<WebElement> monthYearBadges = driver.findElements(By.xpath(MonthYearXpath));

		/*Next calendar button*/
		String nextCalendarWindowXpath = "//div[@class= 'bui-calendar__control bui-calendar__control--next'] ";



		 /*Find Month and YEAR, then select the DAY for the "TO travel date"*/
		while (true) {
			monthYearBadges = driver.findElements(By.xpath(MonthYearXpath));
			System.out.println("To date MYEAR: "+monthYearBadges.get(0).getText());

			if(monthYearBadges.get(0).getText().trim().contains(toMonth + " " + toYear)) {
				String fromDateCellXpath = "//*[@id='frm']/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr/td";
				List<WebElement>  fromDateCells = driver.findElements(By.xpath(fromDateCellXpath));
				for (WebElement fromDateCell : fromDateCells) {
					 if(fromDateCell.getText().equals(toDay)) {
					     fromDateCell.click();
					     try {
						  Thread.sleep(2000);
						 } catch (InterruptedException e) {
                                                      System.out.println(e.getMessage());
						 }
						break;
						 }
					     }
					      break;
				         }else if(monthYearBadges.get(1).getText().trim().contains("May 2024")) {
					      String toCellDateXpath = "//*[@id='frm']/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td";
					      List<WebElement>  toDateCells = driver.findElements(By.xpath(toCellDateXpath));
					      for (WebElement toDateCell : toDateCells) {
						      System.out.println("Cell: "+toDateCell.getText());
						     if(toDateCell.getText().equals("1")) {
							   toDateCell.click();
							   try {
								Thread.sleep(2000);
							   } catch (InterruptedException e) {
								System.out.println(e.getMessage());
							   }
					                     break;
							   }
						       }
						            break;
					              }else {
						           try {
							      driver.findElement(By.xpath(nextCalendarWindowXpath)).click();
							      Thread.sleep(2000);
						            }catch( NoSuchElementException noEelement) {
							     System.out.println("we have reached end of the calendar");
							     break;
						            }catch(InterruptedException intrExc) {
							       System.out.println(intrExc.getMessage());
						      }

					      }
				      }
	         }






	    /*This method is a helper for the  method(EnterFromToDate)*/
	    private static void enterFromDate(String fromMonth,String fromYear,String fromDay,String toMonth,String toYear, String toDay) {

			String MonthYearXpath = "//div[@class='bui-calendar__month']";
			//basedriver_wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(MonthYearXpath))));


			/*Month Year badges here*/
			List<WebElement> monthYearBadges = driver.findElements(By.xpath(MonthYearXpath));

			/*Next calendar button*/
			String nextCalendarWindowXpath = "//div[@class= 'bui-calendar__control bui-calendar__control--next'] ";


		    /*Find Month and YEAR, then select the DAY for the "FROM travel date"*/
			while (true) {
				monthYearBadges = driver.findElements(By.xpath(MonthYearXpath));
				System.out.println("FROM date MYEAR: "+ monthYearBadges.get(0).getText());
				if(monthYearBadges.get(0).getText().trim().contains(  fromMonth+ " "+ fromYear)) {
					String fromDateCellXpath = "//*[@id='frm']/div[1]/div[2]/div[2]/div/div/div[3]/div[1]/table/tbody/tr/td";
					List<WebElement>  fromDateCells = driver.findElements(By.xpath(fromDateCellXpath));
					for (WebElement fromDateCell : fromDateCells) {
						System.out.println("Cell: "+fromDateCell.getText());
						 if(fromDateCell.getText().equals(fromDay)) {
							 fromDateCell.click();
							 try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								System.out.println(e.getMessage());
							}
							 break;
						 }

					}

					break;
				}else

					try {
						driver.findElement(By.xpath(nextCalendarWindowXpath)).click();
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							System.out.println(e.getMessage());
						}
					}catch( NoSuchElementException noEelement) {
						System.out.println(">>>>>>we have reached end of the calendar");
						break;
					}
			}

	    }



	    /**
		 *
		 * Click date field, then when calendar is open select dates from Excel file(Data driven test)
		 */
	    public static void ClickDateSelectTravelDates(Object[] obj) {
	    	if(!(obj[0].toString().replace(".0", "").contains("FromMonth"))) {
	    		try {
	    			//basedriver_wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(departDateFieldXpath))));
					clickOnDateField();
		            LandingPage.EnterFromToDate(obj[0].toString().replace(".0", ""), obj[1].toString().replace(".0", ""), obj[2].toString().replace(".0", ""), obj[3].toString().replace(".0", ""), obj[4].toString().replace(".0", ""), obj[5].toString().replace(".0", ""));
				} catch (InterruptedException e) {
					Reporter.log(e.getMessage());
				}
	    	}
	    }





	/**
	 *
	 * @return WebElement destination field
	 */
	public static WebElement  getDestinationField() {
		return driver.findElement(By.xpath(Destination_field_Xpath));
	}





	/**
	 *
	 * enter text on destination field
	 */
	public static void  enterTextOnDestinationField(String destination) {
		try {
			basedriver_wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Destination_field_Xpath)));
			getDestinationField().click();
			try {
				Thread.sleep(5000);
				getDestinationField().sendKeys(destination);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				Reporter.log(e.getMessage());
			}
		}catch(TimeoutException t_out) {
			Reporter.log(t_out.getMessage());
		}
	}





	public static WebElement getSearchButtonElement() {
		return driver.findElement(By.xpath(Search_button_xpath));
	}







	//Booking.com URL
	public static String getBookingUrl() {
		return booking_webst_url;
	}



	/**
	 *
	 * get next page button icon at screen bottom
	 */
	public static WebElement getNextPageElement() {
		return driver.findElement(By.xpath(next_page_button_xpath));
	}


}
