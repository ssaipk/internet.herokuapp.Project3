package com.internet.herokuapp;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.HomePage;

public class TestHomePage {

	static WebDriver driver;
	static String path = "C:\\Users\\gopir\\eclipse-workspace\\The-internet.herokuapp\\src\\test\\resources\\configfiles\\Config.Properties";
	static Base m = new Base();
	static String HomePropPath = "C:\\Users\\Sreen\\git\\repository\\herokuapp\\src\\test\\resources\\testdata\\HomePage.properties";

	@BeforeClass
	public void Launch() throws Exception {

		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		DesiredCapabilities cp=new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(Method.ReadPropertyFile(path, "testUrl"));
		
	}

	@Test(description = "Verify that the application is launching successfully or not", priority = 1)
	public void TC1() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		String header = driver.findElement(HomePage.Header).getText();
		String expectedHeader = Method.ReadPropertyFile(HomePropPath, "header");
		m.navigation(header, expectedHeader);

	}

	@Test(description = "Verify that all the links are active and enabled to be clicked or not", priority = 2)
	public void TC2() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement link : links) {
			m.activeValidation(link);
		}
	}
}
