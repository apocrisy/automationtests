import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class e2e {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// click from and to
		driver.findElement(By.xpath("//input[@value='Departure City']")).click();
		driver.findElement(By.xpath("//a[@value='DEL']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"))
				.click();

		// family and friends checkbox
		driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

		// select todays date
		driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active")).click();

		// one way trip radio
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
		if (driver.findElement(By.id("Div1")).getAttribute("style").contains("0.5")) {
			System.out.println("It's disabled");
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

		// 5 passangers

		// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.findElement(By.id("divpaxinfo")).click();

		Select NrAdult = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Adult")));
		NrAdult.selectByValue("5");

		Select NrKids = new Select(driver.findElement(By.id("ctl00_mainContent_ddl_Child")));
		NrKids.selectByValue("2");

		Select currency = new Select(
				driver.findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']")));
		currency.selectByVisibleText("USD");

		driver.findElement(By.cssSelector("#ctl00_mainContent_btn_FindFlights")).click();

		driver.quit();

	}

}
