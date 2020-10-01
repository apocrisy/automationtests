import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EurosenderAssignment {

	public static void main(String[] args) {
		
		//Initialize driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//implicit wait added for the form to load
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		// Step 1, open Eurosender URL
		driver.get("https://www.eurosender.com");
		
		// Step 2, From the FROM(PICKUP) country select Slovenia and as TO(DELIVERY) select Austria.
		WebElement pickupLocation = driver.findElement(By.id("from_id"));
		pickupLocation.click();
		driver.findElement(By.xpath("//div[contains(text(),'Slovenia')]")).click();
		
		WebElement deliveryLocation = driver.findElement(By.id("to_id"));
		deliveryLocation.click();
		driver.findElement(By.xpath("//div[contains(text(),'Austria')]")).click();
		
		//Step 3: From the “Who orders?” field validate if the default selected option is “Private person” and asserts its state.  
		//Using appropriate selectors with type=radio, (test validation of isSelected(); method in console for both buttons) assert true is used so the test breaks if return is false.
		
		WebElement personRadioBtn = driver.findElement(By.xpath("//input[@id='person']"));
		System.out.println("personRadioBtn is " + personRadioBtn.isSelected() + " by default.");
		
		WebElement companyRadioBtn = driver.findElement(By.xpath("//input[@id='company']"));
		System.out.println("companyRadioBtn is " + companyRadioBtn.isSelected() + " by default.");

		Assert.assertTrue(personRadioBtn.isSelected(), "Error: Private person is not selected by default!");
		
		
		//Step 4 From the extendable form validate if only “Package or suitcase” has quantity “1”
		//using xpath selector got back all the elements having key value pairs of value='1' (1 element matching in browser)
		
		 List<WebElement> quantityOneDefault = driver.findElements(By.xpath("//input[@value='1']"));
		 
		 ArrayList<String> wElementsActual = new ArrayList<String>();
				 
		for (int i=0; i<quantityOneDefault.size();i++) {
					 
			 String quantityOneList = quantityOneDefault.get(i).getAttribute("id");
					 
			 System.out.println(quantityOneList);
					 
			 wElementsActual.add(quantityOneList);
					
	       	 }
		
		System.out.println(wElementsActual);
		
		List<String> wElementsExpected = Arrays.asList("packages");
		
		Assert.assertEquals(wElementsActual, wElementsExpected);
		
		//Step 5 Validate if the other service types in the extendable form have quantity “0” 
		//using xpath selector got back all the elements having key value pairs of value='0' (4 elements matching in browser)

		 List<WebElement> quantityNull = driver.findElements(By.xpath("//input[@value='0']"));
		 		 
		 ArrayList<String> wElements = new ArrayList<String>();
		
		 
		 for (int i=0; i<quantityNull.size();i++) {
			 
			 String selectionName = quantityNull.get(i).getAttribute("id");
			 
			 wElements.add(selectionName);
			
		 }
		 
		 System.out.println(wElements);
		
		 
		 List<String> expectedList = Arrays.asList("envelopes","pallets","vans","non-standard");
		 System.out.println(expectedList);

		 Assert.assertEquals(wElements, expectedList);
		 
		 
		 //Step 6 click continue (since there is a button with the same name for tablet (on index 0) a parent / child selector is used)
		 
		 driver.findElement(By.cssSelector("div[class*=priceSection_desktop] button[type=submit]")).click();
		 
		
		 //Step 7 On the “Order details” page validate two “Shipping options” (Regular, Express)
		 
		 WebElement regularField = driver.findElement(By.cssSelector("label[for=regular]"));
		 WebElement expressField = driver.findElement(By.cssSelector("label[for=express]"));
		 
		 Assert.assertTrue(regularField.isDisplayed());
		 System.out.println("Regular label is displayed");
		 Assert.assertTrue(expressField.isDisplayed());
		 System.out.println("Express label is displayed");
		 
		 //Step 8 Validate if default selection is “Regular” by asserting its name and current state (selected/deselected)
		 
		 WebElement regularDefault = driver.findElement(By.id("regular"));
		 Assert.assertTrue(regularDefault.isSelected());
		 System.out.println("Regular shipping option is selected by default.");
		 
		 //Step 9 Store the current order service price 
		 
		 WebElement priceBreakdown = driver.findElement(By.cssSelector("div[class*=priceValue"));
		 String storedRegularPrice = priceBreakdown.getText();
		 
		 System.out.println("Price for a regular Shipping option is " + storedRegularPrice);
		 
		 
		 //Step 10 Validate if “Express” button has the correct text “Express Delivery time: EU in 24h / Globally in 48-72h” if validation is passing click the button 
		 // Div cointains text "Express" followed by /p "Delivery time: EU in 24h / Globally in 48-72h" making Java get a newline \n which I removed in the String for assertion.
		 String actualExpressText = expressField.getText().replace("\n", " ");
		 String expectedExpressText = "Express Delivery time: EU in 24h / Globally in 48-72h";
		 
		 
		 System.out.println(actualExpressText);
		 System.out.println(expectedExpressText);
		 
		 Assert.assertEquals(actualExpressText,expectedExpressText, "Actual express text is not equal to expected express text");
		 
		 expressField.click();
		
		 
		 //Step 11 	Confirm if the price has changed 
		 //Can be done with an IF statement or AssertNotEquals
		 
		 WebElement newPrice = driver.findElement(By.cssSelector("div[class*=priceValue"));
		 String storedExpressPrice = newPrice.getText();
		 System.out.println("Price for the express shipping option is " + storedExpressPrice);
		 if (storedRegularPrice != storedExpressPrice)
		 {
			
		 }
		 else 
		 {
			Assert.fail("The price did not change");
		 }
	
		//Step 12 Validate “Shipment details” by validating visibility of input fields and their name (Sender, address, etc) 
		//Specified elements for validation (input fields) are NOT a part of the "Shipment details" wrapper.
		 //Sender field assertions
		 
		 List<WebElement> pickupList = driver.findElements(By.cssSelector("input[id*='pickup']"));   
		
		 for (WebElement pickupElement : pickupList) {
			 
			 System.out.println(pickupElement.getAttribute("id"));
			 Assert.assertTrue(pickupElement.isDisplayed());
			
		 }
		 
		 //Delivery field assertions
		 		 
         List<WebElement> deliveryId = driver.findElements(By.cssSelector("input[id*='delivery']"));  
		
		 for (WebElement deliveryElement : deliveryId) {
			 
			 System.out.println(deliveryElement.getAttribute("id"));
			 Assert.assertTrue(deliveryElement.isDisplayed());
			
		 }
		 		
		driver.quit();
	}

}
