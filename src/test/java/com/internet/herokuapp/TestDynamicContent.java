package com.internet.herokuapp;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
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
import PageObjects.DynamicContent;
import PageObjects.HomePage;

public class TestDynamicContent {

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
		driver.findElement(HomePage.DynamicContent).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement img1 = driver.findElement(DynamicContent.Firstimg);
		WebElement img2 = driver.findElement(DynamicContent.Secondimg);
		WebElement img3 = driver.findElement(DynamicContent.Thirdimg);

		String img1source = (String) jsExecutor.executeScript("return arguments[0].src;", img1);
		String img2source = (String) jsExecutor.executeScript("return arguments[0].src;", img2);
		String img3source = (String) jsExecutor.executeScript("return arguments[0].src;", img3);

		driver.navigate().refresh();

		WebElement img1a = driver.findElement(DynamicContent.Firstimg);
		WebElement img2a = driver.findElement(DynamicContent.Secondimg);
		WebElement img3a = driver.findElement(DynamicContent.Thirdimg);

		String img1asource = (String) jsExecutor.executeScript("return arguments[0].src;", img1a);
		String img2asource = (String) jsExecutor.executeScript("return arguments[0].src;", img2a);
		String img3asource = (String) jsExecutor.executeScript("return arguments[0].src;", img3a);

		String img1aSource = img1a.getAttribute("src");
		String img2aSource = img2a.getAttribute("src");
		String img3aSource = img3a.getAttribute("src");

		Assert.assertNotEquals(img1source, img1aSource, "Test Failed: Image 1 did not change after refreshing");
		Assert.assertNotEquals(img2source, img2aSource, "Test Failed: Image 2 did not change after refreshing");
		Assert.assertNotEquals(img3source, img3aSource, "Test Failed: Image 3 did not change after refreshing");

	}
}
