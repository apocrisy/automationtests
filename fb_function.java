import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class fb_function {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com/groups/792633707439427/permalink/3132667290102712/");
		driver.manage().window().maximize();
		
		
		driver.findElement(By.id("email")).sendKeys("ljubomir.kolarski1992@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("Light_anakin.15");
		driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		WebElement textBox = driver.findElement(By.xpath("//*[@role='textbox']"));
		textBox.sendKeys("123", Keys.ENTER);
	
		
		
		
	}

}
