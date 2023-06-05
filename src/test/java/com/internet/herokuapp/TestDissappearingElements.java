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
import PageObjects.DisappearingElements;
import PageObjects.HomePage;

public class TestDissappearingElements {

	static WebDriver driver;
	static String path = "C:\\Users\\Sreen\\git\\repository\\herokuapp\\src\\test\\resources\\configfiles\\config.properties";
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
		driver.findElement(HomePage.DisappearingElements).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() {

		WebElement Home = driver.findElement(DisappearingElements.Home);
		WebElement About = driver.findElement(DisappearingElements.About);
		WebElement ContactUs = driver.findElement(DisappearingElements.ContactUs);
		WebElement Portfolio = driver.findElement(DisappearingElements.Portfolio);
		driver.navigate().refresh();
		driver.navigate().refresh();
		WebElement Gallery = driver.findElement(DisappearingElements.Gallery);

		Assert.assertTrue(Gallery.isDisplayed(), "Test Failed, element is not appearing");

		driver.navigate().refresh();

		try {
			Assert.assertTrue(Gallery.isDisplayed(), "Test Failed, element is not appearing");
		} catch (Exception e) {
			System.out.println("Test passed element dissappeared");
		}

	}

}
