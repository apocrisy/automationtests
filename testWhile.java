import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class testWhile {

	public static void main(String[] args){
		
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com/challenging_dom");
		driver.manage().window().maximize();
		
		
		int i= 1;
		
		while(i <5) {
			 driver.findElement(By.xpath("//*[@class='button alert']")).click();
			
			i++;
		}
		

	}

}
