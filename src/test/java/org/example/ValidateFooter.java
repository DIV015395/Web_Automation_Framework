package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.com.base.Base;
import org.example.com.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ValidateFooter extends Base {
	
	public WebDriver driver;
	public Actions actions;
	
	private Logger log = LogManager.getLogger(ValidateFooter.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized.");
		actions = new Actions(driver);
		actions.navigateTo(prop.getProperty("url"));
	}
	
	@Test
	public void checkFooter() throws IOException {

		HomePage hp = new HomePage(driver);
		
		Assert.assertTrue(hp.getFooter().isDisplayed());
		log.info("Footer is displayed");

	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Driver is closed");
	}
}
