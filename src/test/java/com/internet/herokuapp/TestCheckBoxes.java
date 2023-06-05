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
import PageObjects.CheckBoxes;
import PageObjects.HomePage;

public class TestCheckBoxes {

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
		driver.findElement(HomePage.Checkboxes).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() {

		WebElement checkbox1 = driver.findElement(CheckBoxes.Checkbox1);
		WebElement checkbox2 = driver.findElement(CheckBoxes.Checkbox2);
		m.activeValidation(checkbox1);
		m.activeValidation(checkbox2);
		if (checkbox1.isSelected() == false) {
			checkbox1.click();
		}
		if (checkbox2.isSelected() == false) {
			checkbox2.click();
		}
		
		Assert.assertTrue(checkbox1.isSelected(),"Test failed, unable to select checkbox 1");
		Assert.assertTrue(checkbox2.isSelected(),"Test failed, unable to select checkbox 2");
	}
}
