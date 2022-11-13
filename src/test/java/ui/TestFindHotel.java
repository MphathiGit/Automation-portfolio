package ui;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.Utils;
import common.BaseTest;




public class TestFindHotel  extends BaseTest{




	/*Load the page*/
	@Test(priority = 1, description = "load the URL page from booking.com")
	public void loadUrl() {
		driver.get(LandingPage.getBookingUrl());
	}



    /**
     * Now Enter text on destination, use Colombia
     */
	@Test(priority = 2, dependsOnMethods = "loadUrl",  description = "Enter destination(Colombia), then selct dates and fire the search")
	public void selectDestinationAndSearch() {
		LandingPage.enterTextOnDestinationField("Colombia");
	}





	 /**
     * Now select from and to date
	 * @throws InterruptedException
     */
    @Test(priority = 3, dependsOnMethods = "selectDestinationAndSearch",  dataProviderClass = DataProviders.class,dataProvider = "excelDates" , description =  "select travel dates")
	public void selectDates(Object[] obj) throws InterruptedException {
    	//System.out.println(obj[0].toString().replace(".0", "") + " "+ obj[1].toString().replace(".0", "")+" "+ obj[2].toString().replace(".0", "")+" "+ obj[3].toString().replace(".0", "")+" "+obj[4].toString().replace(".0", "")+" "+ obj[5].toString().replace(".0", ""));
    	LandingPage.ClickDateSelectTravelDates(obj);
    }





    /**
     * Now click on search button
     */
   @Test(priority = 4,dependsOnMethods = "selectDates", description = "click on search buttin" )
   public void search() {
	   LandingPage.getSearchButtonElement().click();
   }




     /**
      * Scroll down while printing hotel prices, then once i find the one with right price Range, STOP and click on it, when its opened
      * verify that the page URL no more the same
     */
      @Test(priority = 5,dependsOnMethods = "search",  description  = "scroll and find right priced hotel, then stop and click it")
      public void clickHotelonBudget() {
            int fromPrice =144000 ;
            int toPrice = 180000;
            SearchResultsPage.scrollAndFind(fromPrice,toPrice);
			Assert.assertEquals((SearchResultsPage.getCurrentWindowUrl() != SearchResultsPage.getOldWindowUrl()), true);
            Utils.closeCurrentTab();
      }





 }






