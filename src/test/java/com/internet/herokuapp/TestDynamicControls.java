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
import PageObjects.DynamicControls;
import PageObjects.HomePage;

public class TestDynamicControls {

	static WebDriver driver;
	static String path = "C:\\Users\\gopir\\eclipse-workspace\\The-internet.herokuapp\\src\\test\\resources\\configfiles\\Config.Properties";
	static Base m = new Base();
	static String dynamicDataPath = "C:\\Users\\Sreen\\git\\repository\\herokuapp\\src\\test\\resources\\testdata\\DynamicControls.properties";

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
		driver.findElement(HomePage.DynamicControls).click();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

	@Test
	public void TC01() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement chkbox = driver.findElement(DynamicControls.checkbox);
		chkbox.click();
		driver.findElement(DynamicControls.remove).click();
		String message = driver.findElement(DynamicControls.message).getText();
		String expectedmessage = Method.ReadPropertyFile(dynamicDataPath, "removeMessage");
		Assert.assertEquals(message, expectedmessage, "Test failed, check box not removed");
		try {
			Assert.assertFalse(chkbox.isDisplayed(),
					"Test failed, check box still available after clicking on remove button");

		} catch (Exception e) {
			System.out.println("Test Passed, Checkbox deleted");
		}
	}

	@Test
	public void TC02() throws Exception {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

		driver.findElement(DynamicControls.add).click();
		String message = driver.findElement(DynamicControls.message).getText();
		String expectedmessage = Method.ReadPropertyFile(dynamicDataPath, "addMessage");
		Assert.assertEquals(message, expectedmessage, "Test failed, check box not added");

		try {
			WebElement chkbox = driver.findElement(DynamicControls.checkbox);
			Assert.assertTrue(chkbox.isDisplayed(),
					"Test failed, check box still not available after clicking on add button");

		} catch (Exception e) {
			System.out.println("Test Passed, Checkbox added");
		}
	}

	@Test
	public void TC03() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement input = driver.findElement(DynamicControls.input);
		if (input.isEnabled() == false) {

			driver.findElement(DynamicControls.enable).click();
		}
		Thread.sleep(7000);
		Assert.assertTrue(input.isEnabled(), "Test failed, the input box is not enabled");
		String value = "Test";
		input.sendKeys(value);
		String enteredValue = input.getAttribute("value");
		Assert.assertEquals(value, enteredValue, "Entered values are not equal, input box is not working properly");
	}

	@Test
	public void TC04() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		WebElement input = driver.findElement(DynamicControls.input);
		driver.findElement(DynamicControls.disable).click();
		Thread.sleep(7000);
		Assert.assertFalse(input.isEnabled(),
				"Test failed, the expected element is still enable after clicking on disable");

	}
}
