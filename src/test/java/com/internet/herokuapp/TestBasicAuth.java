package com.internet.herokuapp;

import java.time.Duration;

import org.openqa.selenium.Alert;
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

public class TestBasicAuth {

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
		driver.findElement(HomePage.BasicAuth).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}
	
	@Test
	public void TC01() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		Alert a = driver.switchTo().alert();
		a.sendKeys(Method.ReadPropertyFile(path, "UID"));
		a.sendKeys("\t");
		a.sendKeys(Method.ReadPropertyFile(path, "pass"));
		a.accept();
	}
}
