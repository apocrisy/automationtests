package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class exploratoryTest {

	public void addElementTest() {
		
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
		
		driver.manage().window().maximize();
		
		WebElement addElement = driver.findElement(By.xpath("//button[@contains(@onclick, 'addElemen')]"));
		addElement.click();
		
		
		
		
		
	}
	
}
