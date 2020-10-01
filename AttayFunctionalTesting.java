import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AttayFunctionalTesting {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		int j=0;
		
		//expected array
		String[] itemsNeeded= {"Cucumber", "Brocolli", "Beetroot"};
		
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		driver.manage().window().maximize();
		
		//fetch cucumber, get each element and text of it.
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		for(int i=0;i<products.size();i++)
		{
			
			String[] name=products.get(i).getText().split("-");
			String formattedName=name[0].trim();
			//format text to get actual vegetable name instead of "vegetable - 1 KG"
			
			// convert array into array list for easy search
			List itemsNeededList = Arrays.asList(itemsNeeded);
			
			

			if(itemsNeededList.contains(formattedName))
			{
				j++;
				//click on Add to cart. i becomes cucumbers index, with .get(i) we get cucumbers index and click it
				driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				
				if(j==3)
				{
					break;
				}
			}
			
		}

		
	}

}
