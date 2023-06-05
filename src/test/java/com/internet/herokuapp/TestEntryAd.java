package com.internet.herokuapp;

import java.time.Duration;

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
import PageObjects.EntryAd;
import PageObjects.HomePage;

public class TestEntryAd {

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
		driver.findElement(HomePage.EntryAd).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test(description = "Verify that ad is displayed after entering into page", priority = 1)
	public void TC01() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Thread.sleep(2000);
		WebElement ad = driver.findElement(EntryAd.ad);
		WebElement close = driver.findElement(EntryAd.close);
		Assert.assertTrue(ad.isDisplayed(), "Test failed, add is not displayed");

	}

	@Test(description = "Verify that ad is closing after clicking on close button", priority = 2)
	public void TC02() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement ad = driver.findElement(EntryAd.ad);
		WebElement close = driver.findElement(EntryAd.close);
		close.click();
		Thread.sleep(2000);
		Assert.assertFalse(ad.isDisplayed(), "Test failed, add is not displayed");

	}

}
