package com.internet.herokuapp;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.ABTesting;
import PageObjects.HomePage;

public class TestABTessting {
	
	static WebDriver driver;
	static String path = "C:\\Users\\gopir\\eclipse-workspace\\The-internet.herokuapp\\src\\test\\resources\\configfiles\\Config.Properties";
	static Base m = new Base();
	static String ABTestpath = "C:\\Users\\gopir\\eclipse-workspace\\The-internet.herokuapp\\src\\test\\resources\\configfiles\\Config.Properties";

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

	@BeforeMethod
	public void ToABTesting() {
		driver.findElement(HomePage.ABTesting).click();
	}

	@AfterMethod
	public void ToHomepage() throws Exception {
		driver.navigate().back();
	}

	@AfterClass
	public void terminate() {
		driver.quit();
	}

	@Test(description = "Verify that site is redirected to AB Testing page", priority = 1)
	public void TC01() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		String header = driver.findElement(ABTesting.Header).getText();
		String ExpectedHeader = Method.ReadPropertyFile(ABTestpath, "header");
		m.navigation(header, ExpectedHeader);

	}

	@Test(description = "Verify that the AB Testing content is available or not", priority = 2)
	public void TC02() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement content = driver.findElement(ABTesting.Content);
		m.validation(content);
	}

}
