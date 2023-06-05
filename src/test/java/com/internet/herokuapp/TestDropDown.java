package com.internet.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.Dropdown;
import PageObjects.HomePage;

public class TestDropDown {

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
		driver.findElement(HomePage.Dropdown).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() throws Exception {
		WebElement select = driver.findElement(Dropdown.Select);
		Select s = new Select(select);
		s.selectByIndex(1);
		String Selected1 = select.getAttribute("value");
		Assert.assertEquals(Selected1, "1", "Test Failed, desired option not selected");
		s.selectByIndex(2);
		String Selected2 = select.getAttribute("value");
		Assert.assertEquals(Selected2, "2", "Test Failed, desired option not selected");

	}
}
