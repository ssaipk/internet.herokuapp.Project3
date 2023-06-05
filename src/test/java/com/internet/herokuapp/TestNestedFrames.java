package com.internet.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.HomePage;

public class TestNestedFrames {

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
		driver.findElement(HomePage.NestedFrames).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() {

		int frameCount = driver.findElements(By.tagName("frame")).size();
		System.out.println("Total frames: " + frameCount);

		for (int i = 0; i < frameCount; i++) {

			driver.switchTo().frame(i);
			System.out.println("Switched to frame: " + i);
			int iframeCount = driver.findElements(By.tagName("frame")).size();
			for (int j = 0; j < iframeCount; j++) {
				System.out.println("Switched to iframe: " + j);
				driver.switchTo().defaultContent();
				System.out.println("Switched back to main content");
			}
		}
	}
}
