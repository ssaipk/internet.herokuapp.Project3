package com.internet.herokuapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.HomePage;
import PageObjects.ShiftingContent;

public class TestShiftingContent {

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
		driver.findElement(HomePage.ShiftingContent).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@AfterMethod
	public void back() throws Exception {
		driver.get(Method.ReadPropertyFile(path, "testUrl"));
		driver.findElement(HomePage.ShiftingContent).click();

	}

	@Test(description = "Shifting elements randomly", priority = 1)
	public void TC01() {
		driver.findElement(ShiftingContent.menu).click();
		driver.findElement(ShiftingContent.random).click();
		WebElement home = driver.findElement(ShiftingContent.home);
		WebElement about = driver.findElement(ShiftingContent.about);
		WebElement contactUs = driver.findElement(ShiftingContent.contactUs);
		WebElement portfolio = driver.findElement(ShiftingContent.portfolio);
		WebElement gallery = driver.findElement(ShiftingContent.gallery);

		home.click();
		driver.navigate().back();
		about.click();
		driver.navigate().back();
		contactUs.click();
		driver.navigate().back();
		portfolio.click();
		driver.navigate().back();
		gallery.click();
		driver.navigate().back();

		System.out.println("Test passed, accessed all elements after shifting elements");
	}

	@Test(description = "Shifting pixels randomly", priority = 2)
	public void TC02() {

		driver.findElement(ShiftingContent.menu).click();
		driver.findElement(ShiftingContent.shiftingPixels).click();
		WebElement home = driver.findElement(ShiftingContent.home);
		WebElement about = driver.findElement(ShiftingContent.about);
		WebElement contactUs = driver.findElement(ShiftingContent.contactUs);
		WebElement portfolio = driver.findElement(ShiftingContent.portfolio);
		WebElement gallery = driver.findElement(ShiftingContent.gallery);

		home.click();
		driver.navigate().back();
		about.click();
		driver.navigate().back();
		contactUs.click();
		driver.navigate().back();
		portfolio.click();
		driver.navigate().back();
		gallery.click();
		driver.navigate().back();

		System.out.println("Test passed, accessed all elements after shifting pixels");

	}
	
	@Test(description = "Shifting pixels and elements randomly", priority = 3)
	public void TC03() {

		driver.findElement(ShiftingContent.menu).click();
		driver.findElement(ShiftingContent.shiftingRandomeAndPixels).click();
		WebElement home = driver.findElement(ShiftingContent.home);
		WebElement about = driver.findElement(ShiftingContent.about);
		WebElement contactUs = driver.findElement(ShiftingContent.contactUs);
		WebElement portfolio = driver.findElement(ShiftingContent.portfolio);
		WebElement gallery = driver.findElement(ShiftingContent.gallery);

		home.click();
		driver.navigate().back();
		about.click();
		driver.navigate().back();
		contactUs.click();
		driver.navigate().back();
		portfolio.click();
		driver.navigate().back();
		gallery.click();
		driver.navigate().back();

		System.out.println("Test passed, accessed all elements after shifting elements and pixels");

	}
}
