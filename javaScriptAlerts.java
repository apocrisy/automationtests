import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class javaScriptAlerts {

	public static void main(String[] args) {
		String text="Ljubomir";
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("name")).sendKeys(text);
		
		//click alert
		driver.findElement(By.cssSelector("[id='alertbtn']")).click();
		//look at alert text
		System.out.println(driver.switchTo().alert().getText());
		//accept alert
		driver.switchTo().alert().accept();
		
	}

}
