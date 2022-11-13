package common;

public class Webpage extends BaseTest{






     //Navigate to page
     public static void navigateToUrl(String url) {
    	 driver.navigate().to(url);
     }



     //Navigate to backwards
     public  static void navigateBack() {
    	 driver.navigate().back();
    	 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     }



     //Navigate to forward
     public static void navigateForward() {
    	 driver.navigate().forward();
     }



}
