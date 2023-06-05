package com.internet.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.AddRemoveElements;
import PageObjects.HomePage;

public class TestAddRemoveElements {

	static WebDriver driver;
	static String path = "C:\\Users\\gopir\\eclipse-workspace\\The-internet.herokuapp\\src\\test\\resources\\configfiles\\Config.Properties";
	static Base m = new Base();

	@BeforeClass
	public void launch() throws Exception {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		DesiredCapabilities cp=new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(Method.ReadPropertyFile(path, "testUrl"));
		driver.findElement(HomePage.AddRemoveElements).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() {
		driver.findElement(AddRemoveElements.AddElementButton).click();
		WebElement elements = driver.findElement(AddRemoveElements.Elements);
		m.validation(elements);
		System.out.println("Element added successfully");
	}

	@Test
	public void TC02() {

		driver.findElement(AddRemoveElements.Delete).click();
		try {
			WebElement elements = driver.findElement(AddRemoveElements.Elements);
			Assert.assertFalse(elements.isDisplayed(), "Test failed, deletion of element is not done");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Test Passed, Element deleted successfully");
			e.printStackTrace();
		}
	}
}
