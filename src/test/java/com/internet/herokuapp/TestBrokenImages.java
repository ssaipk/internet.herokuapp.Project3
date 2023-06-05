package com.internet.herokuapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Methods.Base;
import Methods.Method;
import PageObjects.HomePage;

public class TestBrokenImages {

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
		driver.findElement(HomePage.BrokenImages).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}
	
	@Test
	public void TC01() {
		List<WebElement> images = driver.findElements(By.tagName("img"));

		for (WebElement image : images) {
			String imageUrl = image.getAttribute("src");
			
			try {
				URL url = new URL(imageUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.connect();

				int responseCode = connection.getResponseCode();
				if (responseCode != 200) {
					System.out.println("Broken image found: " + imageUrl);
				}

				connection.disconnect();
			} catch (IOException e) {
				System.out.println("Exception occurred while checking image: " + imageUrl);
				e.printStackTrace();
			}
		}
	}

}
